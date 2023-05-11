package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.models.commands.api.ClearCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.RemoveByIdCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.RemoveGreaterCommand;
import ru.bardinpetr.itmo.lab5.network.app.server.models.requests.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.APIApplication;
import ru.bardinpetr.itmo.lab5.server.dao.workers.IWorkerCollectionDAO;

public class DBRemoveApplication extends APIApplication {

    private final IWorkerCollectionDAO dao;

    public DBRemoveApplication(IWorkerCollectionDAO dao) {
        this.dao = dao;

        on(RemoveGreaterCommand.class, this::onRemoveGreater);
        on(RemoveByIdCommand.class, this::onRemove);
        on(ClearCommand.class, req -> dao.clear());
    }

    private void onRemove(AppRequest appRequest) {
        RemoveByIdCommand req = (RemoveByIdCommand) appRequest.payload();
        if (!dao.remove(req.id))
            throw new RuntimeException("Not found entity to remove");
    }

    private void onRemoveGreater(AppRequest appRequest) {
        RemoveGreaterCommand req = (RemoveGreaterCommand) appRequest.payload();
        dao.remove(cur -> cur.compareTo(req.element) > 0);
    }
}


