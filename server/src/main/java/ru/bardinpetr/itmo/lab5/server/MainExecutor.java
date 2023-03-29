package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.dao.workers.IWorkerCollectionDAO;
import ru.bardinpetr.itmo.lab5.server.dao.workers.WorkerCollectionDAOFactory;
import ru.bardinpetr.itmo.lab5.server.db.filedb.FileDBController;
import ru.bardinpetr.itmo.lab5.server.db.filedb.FileDBControllerFactory;
import ru.bardinpetr.itmo.lab5.server.executor.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.server.executor.WorkersCRUDExecutor;
import ru.bardinpetr.itmo.lab5.server.executor.WorkersSpecialExecutor;

import java.nio.file.Path;

/**
 * Server main controller
 */
public class MainExecutor extends Executor {
    public MainExecutor(Path dbFile) {
        var dbControllerFactory = new FileDBControllerFactory<>(dbFile, WorkerCollection.class);
        var daoFactory = new WorkerCollectionDAOFactory();

        IWorkerCollectionDAO dao;
        try {
            FileDBController<WorkerCollection> ctrl = dbControllerFactory.createController();
            dao = (IWorkerCollectionDAO) daoFactory.createDAO(ctrl);
        } catch (Exception e) {
            System.err.printf("[DB] could not read from file. \n%s", e.getMessage());
            System.exit(1);
            return;
        }

        registerExecutor(new WorkersCRUDExecutor(dao));
        registerExecutor(new WorkersSpecialExecutor(dao));
        registerExecutor(new ScriptExecutor(this));
    }
}
