package ru.bardinpetr.itmo.lab5.server.app;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.dao.workers.IWorkerCollectionDAO;
import ru.bardinpetr.itmo.lab5.server.dao.workers.WorkerCollectionDAOFactory;
import ru.bardinpetr.itmo.lab5.server.db.filedb.FileDBController;
import ru.bardinpetr.itmo.lab5.server.db.filedb.FileDBControllerFactory;

import java.nio.file.Path;

@Slf4j
public class WorkersDAOFactory {
    public IWorkerCollectionDAO createDB() {
        log.info("Initializing Workers DB");

        var dbControllerFactory = new FileDBControllerFactory<>(Path.of("db.xml"), WorkerCollection.class);
        var daoFactory = new WorkerCollectionDAOFactory();

        IWorkerCollectionDAO dao;
        try {
            FileDBController<WorkerCollection> ctrl = dbControllerFactory.createController();
//            ctrl.clear();
            dao = (IWorkerCollectionDAO) daoFactory.createDAO(ctrl);
        } catch (Exception e) {
            log.error("Could not read DB from file. Error: {}", e.getMessage());
            System.exit(1);
            return null;
        }

        log.info("DB ready");
        return dao;
    }
}
