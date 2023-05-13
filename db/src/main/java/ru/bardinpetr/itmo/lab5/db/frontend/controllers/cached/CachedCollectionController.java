package ru.bardinpetr.itmo.lab5.db.frontend.controllers.cached;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.db.backend.DBStorageBackend;
import ru.bardinpetr.itmo.lab5.db.errors.DBBackendIOException;
import ru.bardinpetr.itmo.lab5.db.frontend.controllers.DBController;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;

import java.util.function.Supplier;

/**
 * Database main controller.
 * Stores local collection T and provides interface for storing and loading it to the file.
 */
@Slf4j
public class CachedCollectionController<T> implements DBController {
    private final DBStorageBackend<T> storage;
    private final Supplier<T> collectionSupplier;
    private T collection;

    /**
     * Initializes database.
     * If file exists, data from it is loaded. If not, empty collection created.
     *
     * @param storage db backend controller for storing database
     */
    public CachedCollectionController(DBStorageBackend<T> storage, Supplier<T> collectionSupplier) {
        this.collectionSupplier = collectionSupplier;
        this.storage = storage;

        load();
    }

    /**
     * Load collection from file to local collection.
     * If file is not present or was damaged, it is recreated with empty collection (and empty collection loaded).
     * If there are problems with permissions and issue could not be resolved automatically,
     * returns false and logs for administrator to fix.
     */
    public void load() {
        try {
            collection = storage.loadCollection();
        } catch (DBBackendIOException e) {
            log.warn("DB invalid. Recreating");
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
            storage.storeCollection(collection);
            return true;
        } catch (DBBackendIOException e) {
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
            collection = collectionSupplier.get();
//            collection = collectionSupplier.getDeclaredConstructor().newInstance();
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
    public T collection() {
        return collection;
    }

    /**
     * Returns db backend
     */
    public DBStorageBackend<T> backend() {
        return storage;
    }

    /**
     * Get DB file info
     */
    public CollectionInfo info() {
        return storage.getInfo();
    }
}
