package ru.bardinpetr.itmo.lab5.server.filedb;

import ru.bardinpetr.itmo.lab5.models.serdes.XMLSerDesService;
import ru.bardinpetr.itmo.lab5.models.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.server.filedb.storage.FileStorageController;
import ru.bardinpetr.itmo.lab5.server.filedb.storage.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.server.filedb.storage.exceptions.InvalidDataFileException;
import ru.bardinpetr.itmo.lab5.server.filedb.storage.io.FileIOController;

import java.util.Collection;

/**
 * Database main controller.
 * Stores local WorkerCollection and provides interface for storing and loading it to the file.
 */
public class FileDBController<T extends Collection<?>> {
    private final FileStorageController<T> storage;
    protected T collection;

    /**
     * Initializes database.
     * If file exists, data from it is loaded. If not, empty collection created.
     *
     * @param fileIO file controller for storing database
     */
    public FileDBController(FileIOController fileIO, Class<? extends T> baseClass) {
        storage = new FileStorageController<T>(fileIO, new XMLSerDesService<>(baseClass));
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
        } catch (InvalidDataFileException ignored) {
            System.err.println("[DB] Current file ignored as it contains invalid data. File recreated. Collection cleared");
            clear();
        } catch (FileAccessException e) {
            System.err.printf("[DB] could not read from file. Fix by hand please. Error: %s", e);
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
     * Resets local collection and recreates file
     *
     * @return if operation was successful
     */
    public boolean clear() {
        collection.clear();
        return store();
    }
}
