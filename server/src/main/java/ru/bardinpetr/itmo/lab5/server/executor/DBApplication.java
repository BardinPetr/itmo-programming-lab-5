package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.APIApplication;
import ru.bardinpetr.itmo.lab5.server.api.ExecutorAdapterApplication;
import ru.bardinpetr.itmo.lab5.server.dao.ICollectionDAO;
import ru.bardinpetr.itmo.lab5.server.dao.sync.IOwnedCollectionDAO;
import ru.bardinpetr.itmo.lab5.server.dao.sync.OwnedDAO;

public class DBApplication extends APIApplication {
    private final IOwnedCollectionDAO<Integer, Worker> dao;

    public DBApplication(ICollectionDAO<Integer, Worker> originalDAO) {
        this.dao = new OwnedDAO<>(originalDAO);

        this.chain(new ExecutorAdapterApplication(new DBReadExecutor(dao)))
                .chain(new ExecutorAdapterApplication(new DBPagingExecutor(dao)))
                .chain(new DBInsertApplication(dao))
                .chain(new DBUpdateApplication(dao))
                .chain(new DBRemoveApplication(dao));

        addExitHook();
    }

    private void addExitHook() {
        Runtime
                .getRuntime()
                .addShutdownHook(new Thread(dao::save));
    }
}
