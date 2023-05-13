package ru.bardinpetr.itmo.lab5.db.backend.impl.file;

import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.serdes.XMLSerDesService;
import ru.bardinpetr.itmo.lab5.db.errors.DBCreateException;
import ru.bardinpetr.itmo.lab5.db.frontend.controllers.DBControllerFactory;
import ru.bardinpetr.itmo.lab5.db.frontend.controllers.cached.CachedCollectionController;

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
    public CachedCollectionController<T> createController() throws DBCreateException {
        try {
            var backend = new FileStorageBackend<>(
                    new FileIOController(filePath),
                    new XMLSerDesService<>(baseObjectType)
            );
            return new CachedCollectionController<>(backend, baseObjectType);
        } catch (Exception ex) {
            throw new DBCreateException(ex);
        }
    }
}
