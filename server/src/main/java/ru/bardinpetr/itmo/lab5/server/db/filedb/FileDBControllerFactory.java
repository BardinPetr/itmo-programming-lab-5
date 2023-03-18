package ru.bardinpetr.itmo.lab5.server.db.filedb;

import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.server.db.DBControllerFactory;
import ru.bardinpetr.itmo.lab5.server.db.errors.DBCreateException;

import java.nio.file.Path;

/**
 * Factory to create FileDB controllers of type T
 *
 * @param <T> object stored in the db
 */
public class FileDBControllerFactory<T> implements DBControllerFactory {
    private final String filePath;
    private final Class<T> baseObjectType;

    public FileDBControllerFactory(Path dbFile, Class<T> baseObjectType) {
        this.filePath = dbFile.toString();
        this.baseObjectType = baseObjectType;
    }

    /**
     * Create controller with file and type of this factory
     *
     * @return FileDB controller
     * @throws DBCreateException if db can't be initialized
     */
    @Override
    public FileDBController<T> createController() throws DBCreateException {
        try {
            var io = new FileIOController(filePath);
            return new FileDBController<>(io, baseObjectType);
        } catch (Exception ex) {
            throw new DBCreateException(ex);
        }
    }
}
