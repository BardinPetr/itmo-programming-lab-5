package ru.bardinpetr.itmo.lab5.db.backend.impl.file;

import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.common.serdes.SerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.db.backend.DBStorageBackend;
import ru.bardinpetr.itmo.lab5.db.errors.DBBackendIOException;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;

/**
 * Implements main operations storing collection T in file
 */
public class FileStorageBackend<T> implements DBStorageBackend<T> {
    private final FileIOController file;
    private final SerDesService<T> serDesService;

    /**
     * @param file          controller for file which should be used as local storage
     * @param serDesService serializer/deserializer service to use for storing
     */
    public FileStorageBackend(FileIOController file, SerDesService<T> serDesService) {
        this.file = file;
        this.serDesService = serDesService;
    }

    /**
     * Save object to file.
     * File will be overwritten if exists and create otherwise.
     *
     * @param data source data object corresponding to type of FileDBController
     */
    @Override
    public void storeCollection(T data) throws DBBackendIOException {
        byte[] serialized;
        try {
            serialized = serDesService.serialize(data);
        } catch (SerDesException e) {
            return;
        }
        try {
            file.write(serialized);
        } catch (FileAccessException e) {
            throw new DBBackendIOException(e);
        }
    }

    /**
     * Load data from file.
     * File also may be empty or have invalid structure, however if file not exists FileAccessException will be thrown
     *
     * @return deserialize object
     * @throws DBBackendIOException thrown if file could not be read, file is empty or having corrupted/invalid data
     */
    @Override
    public T loadCollection() throws DBBackendIOException {
        try {
            byte[] data = file.read();
            return serDesService.deserialize(data);
        } catch (SerDesException | FileAccessException ex) {
            throw new DBBackendIOException(ex);
        }
    }

    /**
     * Clears contents or creates empty DB file
     */
    @Override
    public void clearCollection() throws DBBackendIOException {
        try {
            file.clear();
        } catch (FileAccessException e) {
            throw new DBBackendIOException(e);
        }
    }

    @Override
    public CollectionInfo getInfo() {
        return null;
    }
}
