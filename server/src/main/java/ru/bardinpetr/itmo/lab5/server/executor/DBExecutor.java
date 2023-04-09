package ru.bardinpetr.itmo.lab5.server.executor;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.dao.workers.IWorkerCollectionDAO;
import ru.bardinpetr.itmo.lab5.server.dao.workers.WorkerCollectionDAOFactory;
import ru.bardinpetr.itmo.lab5.server.db.filedb.FileDBController;
import ru.bardinpetr.itmo.lab5.server.db.filedb.FileDBControllerFactory;

import java.nio.file.Path;

/**
 * Server main controller
 */
@Slf4j
public class DBExecutor extends Executor {
    public DBExecutor(Path dbFile) {
        log.info("Initializing DB");

        var dbControllerFactory = new FileDBControllerFactory<>(dbFile, WorkerCollection.class);
        var daoFactory = new WorkerCollectionDAOFactory();

        IWorkerCollectionDAO dao;
        try {
            FileDBController<WorkerCollection> ctrl = dbControllerFactory.createController();
            dao = (IWorkerCollectionDAO) daoFactory.createDAO(ctrl);
        } catch (Exception e) {
            log.error("Could not read DB from file. Error: {}", e.getMessage());
            System.exit(1);
            return;
        }

        log.info("DB ready");

        registerExecutor(new WorkersCRUDExecutor(dao));
        registerExecutor(new WorkersSpecialExecutor(dao));
        registerExecutor(new ScriptExecutor(this));

        Runtime
                .getRuntime()
                .addShutdownHook(new Thread(dao::save));
    }
}
