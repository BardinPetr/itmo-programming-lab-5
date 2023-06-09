package ru.bardinpetr.itmo.lab5.common.io.exceptions;

import java.io.File;

/**
 * Exception for handling various file operations
 */
public class FileAccessException extends Exception {
    private final String path;
    private final OperationType type;

    public FileAccessException(Exception originalException, String path, OperationType type) {
//        super(
//                "Failed to access file %s -- %s".formatted(path, type.getDescription()),
//                originalException
//        );
        super("FileAccessException.fileAccessError.text");
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
        READ("OperationType.readError.text"),
        WRITE("OperationType.writeError.text"),
        PERM_READ("OperationType.readPermissionError.text"),
        PERM_WRITE("OperationType.writePermissionError.text"),
        CREATE("OperationType.createPermissionError.text"),
        OPEN("OperationType.invalidPath.text"),
        SET_PERMISSION("OperationType.updatePermissionError.text");

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
