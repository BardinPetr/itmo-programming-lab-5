package ru.bardinpetr.itmo.lab5.server.dao;

import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;

public class WorkersDAO extends CRUDDAO<Long, Worker> {

    public WorkersDAO(FileDBController<WorkerCollection> controller) {
        super(controller);
    }
}
