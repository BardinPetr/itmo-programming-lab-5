package ru.bardinpetr.itmo.lab5.server.db.filedb;

import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.InvalidDataFileException;
import ru.bardinpetr.itmo.lab5.common.serdes.SerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;

/**
 * Implements main operations storing collection T in file
 */
public class FileStorageController<T> {
    private final FileIOController file;
    private final SerDesService<T> serDesService;

    /**
     * @param file          controller for file which should be used as local storage
     * @param serDesService serializer/deserializer service to use for storing
     */
    public FileStorageController(FileIOController file, SerDesService<T> serDesService) {
        this.file = file;
        this.serDesService = serDesService;
    }

    /**
     * Save object to file.
     * File will be overwritten if exists and create otherwise.
     *
     * @param data source data object corresponding to type of FileDBController
     * @throws SerDesException     thrown object could not be serialized (probably not matching definition or constraints)
     * @throws FileAccessException thrown if file could not be written
     */
    public void storeObject(T data) throws SerDesException, FileAccessException {
        byte[] serialized = serDesService.serialize(data);
        file.write(serialized);
    }

    /**
     * Load data from file.
     * File also may be empty or have invalid structure, however if file not exists FileAccessException will be thrown
     *
     * @return deserialize object
     * @throws InvalidDataFileException thrown if file is empty or having corrupted/invalid data
     * @throws FileAccessException      thrown if file could not be read
     */
    public T loadObject() throws FileAccessException, InvalidDataFileException {
        byte[] data = file.read();
        try {
            return serDesService.deserialize(data);
        } catch (SerDesException ex) {
            throw new InvalidDataFileException(file.getPath(), data, ex);
        }
    }

    /**
     * Clears contents or creates empty DB file
     *
     * @throws FileAccessException thrown if file could not be nor recreated, nor cleared
     */
    public void clear() throws FileAccessException {
        file.clear();
    }
}
