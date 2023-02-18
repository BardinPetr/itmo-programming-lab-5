package ru.bardinpetr.itmo.lab5.server.filedb;

import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.InvalidDataFileException;
import ru.bardinpetr.itmo.lab5.common.serdes.XMLSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;

/**
 * Database main controller.
 * Stores local collection T and provides interface for storing and loading it to the file.
 */
public class FileDBController<T> {
    private final FileStorageController<T> storage;
    private final Class<T> baseCollectionClass;
    private T collection;

    /**
     * Initializes database.
     * If file exists, data from it is loaded. If not, empty collection created.
     *
     * @param fileIO file controller for storing database
     */
    public FileDBController(FileIOController fileIO, Class<T> baseCollectionClass) {
        this.baseCollectionClass = baseCollectionClass;

        storage = new FileStorageController<>(fileIO, new XMLSerDesService<>(baseCollectionClass));
        load();
    }

    /**
     * Load collection from file to local collection.
     * If file is not present or was damaged, it is recreated with empty collection (and empty collection loaded).
     * If there are problems with permissions and issue could not be resolved automatically,
     * returns false and logs for administrator to fix.
     *
     * @return if operation was successful
     */
    public boolean load() {
        try {
            collection = storage.loadObject();
        } catch (InvalidDataFileException ex) {
            System.err.println("[DB] DB file recreated as it contained invalid data. Collection cleared");
            clear();
        } catch (FileAccessException e) {
            System.err.printf("[DB] could not read from file. Fix by hand please. \nError: %s", e);
            return false;
        }
        return true;
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
            storage.storeObject(collection);
            return true;
        } catch (SerDesException ignored) {
            System.err.println("[DB] serialization error");
        } catch (FileAccessException e) {
            System.err.printf("[DB] could not write to file. Fix by hand please. Error: %s", e);
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
}
