package ru.bardinpetr.itmo.lab5.server.db.filedb;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.InvalidDataFileException;
import ru.bardinpetr.itmo.lab5.common.serdes.XMLSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.server.db.DBController;

/**
 * Database main controller.
 * Stores local collection T and provides interface for storing and loading it to the file.
 */
@Slf4j
public class FileDBController<T> implements DBController {
    private final FileStorageController<T> storage;
    private final Class<T> baseCollectionClass;
    private final FileIOController fileIO;
    private T collection;

    /**
     * Initializes database.
     * If file exists, data from it is loaded. If not, empty collection created.
     *
     * @param fileIO file controller for storing database
     * @throws FileAccessException thrown if file can't be accessed
     */
    public FileDBController(FileIOController fileIO, Class<T> baseCollectionClass) throws FileAccessException {
        this.baseCollectionClass = baseCollectionClass;
        this.fileIO = fileIO;

        storage = new FileStorageController<>(fileIO, new XMLSerDesService<>(baseCollectionClass));
        load();
    }

    /**
     * Load collection from file to local collection.
     * If file is not present or was damaged, it is recreated with empty collection (and empty collection loaded).
     * If there are problems with permissions and issue could not be resolved automatically,
     * returns false and logs for administrator to fix.
     *
     * @throws FileAccessException if file can't be accessed or created
     */
    public void load() throws FileAccessException {
        try {
            collection = storage.loadObject();
        } catch (InvalidDataFileException ignore) {
            log.warn("\nDB invalid. Recreating");
            clear();
        }
    }

    /**
     * Save local collection copy to file.
     * Overwrites existing.
     * If there are problems with permissions and issue could not be resolved automatically,
     * returns false and logs for administrator to fix.
     *
     * @return if operation was successful
     */
    public boolean store() {
        try {
            log.info("[DB] saving database");
            storage.storeObject(collection);
            return true;
        } catch (SerDesException ignored) {
            log.error("[DB] serialization error");
        } catch (FileAccessException e) {
            log.error("[DB] could not write to file: \n{}\n", e.getMessage());
        }
        return false;
    }

    /**
     * Resets local collection by instantiating empty collection of target type and recreates file
     *
     * @return if operation was successful
     */
    public boolean clear() {
        try {
            collection = baseCollectionClass.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return store();
    }

    /**
     * Get collection object
     *
     * @return collection
     */
    public T data() {
        return collection;
    }

    /**
     * Get DB file info
     */
    public FileDBInfo info() {
        return new FileDBInfo(
                fileIO.getPath(),
                fileIO.creationDate()
        );
    }
}
