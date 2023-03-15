package ru.bardinpetr.itmo.lab5.server.filedb;

import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;

public class FileDBControllerFactory {
    public static <T> FileDBController<T> create(String filePath, Class<T> collectionClass) throws FileAccessException {
        return new FileDBController<>(createFileController(filePath), collectionClass);
    }

    public static FileIOController createFileController(String path) throws FileAccessException {
        return new FileIOController(path);
    }
}
