package ru.bardinpetr.itmo.lab5.server.filedb;

import ru.bardinpetr.itmo.lab5.models.serdes.SerDesService;
import ru.bardinpetr.itmo.lab5.models.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.server.filedb.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.server.filedb.exceptions.InvalidDataFileException;
import ru.bardinpetr.itmo.lab5.server.filedb.io.FileIOController;

/**
 * Implements main operations storing collection T in file
 */
public class FileDBController<T> {
    private final FileIOController file;
    private final SerDesService<T> serDesService;

    /**
     * @param file          controller for file which should be used as local storage
     * @param serDesService serializer/deserializer service to use for storing
     */
    public FileDBController(FileIOController file, SerDesService<T> serDesService) {
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
    public void store(T data) throws SerDesException, FileAccessException {
        byte[] serialized = serDesService.serialize(data);
        file.write(serialized);
    }

    /**
     * Load data from file.
     * File also may be empty or have invalid structure, however if file not exists FileAccessException will be thrown
     *
     * @param targetClass target class to construct with deserializer
     * @return deserialize object
     * @throws InvalidDataFileException thrown if file is empty or having corrupted/invalid data
     * @throws FileAccessException      thrown if file could not be read
     */
    public T load(Class<? extends T> targetClass) throws FileAccessException, InvalidDataFileException {
        byte[] data = file.read();
        try {
            return serDesService.deserialize(data, targetClass);
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
