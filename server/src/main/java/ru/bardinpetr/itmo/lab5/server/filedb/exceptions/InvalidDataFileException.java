package ru.bardinpetr.itmo.lab5.server.filedb.exceptions;

import ru.bardinpetr.itmo.lab5.models.serdes.exceptions.SerDesException;

/**
 * Thrown if DB controller tried to load corrupted or empty file
 */
public class InvalidDataFileException extends Exception {
    private final String path;
    private final byte[] contents;
    private final SerDesException originalException;

    /**
     * @param path              DB file path
     * @param contents          Full contents of invalid file
     * @param originalException Exception thrown by deserializer
     */
    public InvalidDataFileException(String path, byte[] contents, SerDesException originalException) {
        this.path = path;
        this.contents = contents;
        this.originalException = originalException;
    }

    public String getPath() {
        return path;
    }

    public byte[] getContents() {
        return contents;
    }

    /**
     * @return deserializer's exception caused this error
     */
    public SerDesException getOriginalException() {
        return originalException;
    }
}
