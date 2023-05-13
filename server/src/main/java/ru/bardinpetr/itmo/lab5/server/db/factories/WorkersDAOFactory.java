package ru.bardinpetr.itmo.lab5.server.db.factories;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.db.backend.impl.file.FileDBControllerFactory;
import ru.bardinpetr.itmo.lab5.db.frontend.controllers.cached.CachedCollectionController;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.db.dao.ctrl.IWorkerCollectionDAO;

import java.nio.file.Path;

@Slf4j
public class WorkersDAOFactory {
    public IWorkerCollectionDAO createDB() {
        log.info("Initializing Workers DB");

        var dbControllerFactory = new FileDBControllerFactory<>(Path.of("db.xml"), WorkerCollection.class);
        var daoFactory = new WorkerCollectionDAOFactory();

        IWorkerCollectionDAO dao;
        try {
            CachedCollectionController<WorkerCollection> ctrl = dbControllerFactory.createController();
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
