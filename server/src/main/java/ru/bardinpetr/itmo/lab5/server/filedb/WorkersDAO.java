package ru.bardinpetr.itmo.lab5.server.filedb;

import ru.bardinpetr.itmo.lab5.models.data.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.filedb.storage.io.FileIOController;

public class WorkersDAO extends FileDBController<WorkerCollection> {
    /**
     * Initializes database.
     * If file exists, data from it is loaded. If not, empty collection created.
     *
     * @param fileIO file controller for storing database
     */
    public WorkersDAO(FileIOController fileIO) {
        super(fileIO, WorkerCollection.class);
    }
}
