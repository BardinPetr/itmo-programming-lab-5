package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddIfMaxCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddIfMinCommand;
import ru.bardinpetr.itmo.lab5.server.dao.workers.IWorkerCollectionDAO;

public class DBInsertExecutor extends Executor {
    private final IWorkerCollectionDAO dao;

    public DBInsertExecutor(IWorkerCollectionDAO dao) {
        this.dao = dao;

        registerOperation(
                AddCommand.class,
                req -> {
                    var resp = req.createResponse();
                    resp.setId(dao.add(req.element));
                    return resp;
                }
        );
        registerVoidOperation(AddIfMaxCommand.class, this::addIfMax);
        registerVoidOperation(AddIfMinCommand.class, this::addIfMin);

    }

    private void addIfMax(AddIfMaxCommand req) {
        var cur = req.element;
        var curMax = dao.getMax();
        if (curMax == null) throw new RuntimeException("No elements");
        if (curMax.compareTo(cur) >= 0) throw new RuntimeException("Not maximum");
        dao.add(cur);
    }

    private void addIfMin(AddIfMinCommand req) {
        var cur = req.element;
        var curMin = dao.getMin();
        if (curMin == null) throw new RuntimeException("No elements");
        if (curMin.compareTo(cur) <= 0) throw new RuntimeException("Not minimum");
        dao.add(cur);
    }
}
