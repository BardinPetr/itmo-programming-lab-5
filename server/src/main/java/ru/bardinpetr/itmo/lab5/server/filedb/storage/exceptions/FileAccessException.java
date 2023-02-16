package ru.bardinpetr.itmo.lab5.server.filedb.storage.exceptions;

import java.io.File;

/**
 * Exception for handling various file operations
 */
public class FileAccessException extends Exception {
    private final String path;
    private final OperationType type;

    public FileAccessException(Exception originalException, String path, OperationType type) {
        super(
                "Failed to get %s access to file %s: %s".formatted(
                        type.name(), path, type.getDescription()),
                originalException
        );
        this.type = type;
        this.path = path;
    }

    public FileAccessException(String path, OperationType type) {
        this(null, path, type);
    }

    public FileAccessException(Exception originalException, File file, OperationType type) {
        this(originalException, file.getAbsolutePath(), type);
    }

    public FileAccessException(File file, OperationType type) {
        this(null, file.getAbsolutePath(), type);
    }

    /**
     * @return file on which failed operation was tried to be executed
     */
    public String getPath() {
        return path;
    }

    /**
     * @return type of failed operation
     */
    public OperationType getType() {
        return type;
    }

    /**
     * Types for actions causing exception
     */
    public enum OperationType {
        READ, WRITE,
        PERM_READ("no read permission for current user"),
        PERM_WRITE("no write permission for current user"),
        CREATE,
        OPEN("may be not a regular file or have invalid path"),
        SET_PERMISSION("failed to update permissions");

        private final String description;

        OperationType(String description) {
            this.description = description;
        }

        OperationType() {
            this.description = name();
        }

        public String getDescription() {
            return description;
        }
    }
}
