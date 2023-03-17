package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.dao.workers.FileDBWorkersDAO;
import ru.bardinpetr.itmo.lab5.server.executor.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.server.executor.WorkersCRUDExecutor;
import ru.bardinpetr.itmo.lab5.server.executor.WorkersSpecialExecutor;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;

public class MainExecutor extends Executor {

    public MainExecutor(FileIOController fileIOController) {
        FileDBController<WorkerCollection> db;
        try {
            db = new FileDBController<>(fileIOController, WorkerCollection.class);
        } catch (FileAccessException e) {
            System.err.printf("[DB] could not read from file. \n%s", e.getMessage());
            System.exit(1);
            return;
        }

        var dao = new FileDBWorkersDAO(db);
        registerExecutor(new WorkersCRUDExecutor(dao));
        registerExecutor(new WorkersSpecialExecutor(dao));
        registerExecutor(new ScriptExecutor(this));
    }
}
