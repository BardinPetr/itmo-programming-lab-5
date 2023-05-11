package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.APIApplication;
import ru.bardinpetr.itmo.lab5.server.api.ExecutorAdapterApplication;
import ru.bardinpetr.itmo.lab5.server.dao.workers.IWorkerCollectionDAO;

public class DBApplication extends APIApplication {

    private final IWorkerCollectionDAO dao;

    public DBApplication(IWorkerCollectionDAO dao) {
        this.dao = dao;

        this.chain(new ExecutorAdapterApplication(new DBReadExecutor(dao)))
                .chain(new ExecutorAdapterApplication(new DBPagingExecutor(dao)))
                .chain(new ExecutorAdapterApplication(new DBInsertExecutor(dao)))
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
