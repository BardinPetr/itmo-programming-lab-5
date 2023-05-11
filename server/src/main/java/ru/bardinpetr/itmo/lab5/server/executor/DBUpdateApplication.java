package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.models.commands.api.UpdateCommand;
import ru.bardinpetr.itmo.lab5.network.app.server.models.requests.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.APIApplication;
import ru.bardinpetr.itmo.lab5.server.dao.workers.IWorkerCollectionDAO;

public class DBUpdateApplication extends APIApplication {
    private final IWorkerCollectionDAO dao;

    public DBUpdateApplication(IWorkerCollectionDAO dao) {
        this.dao = dao;
        on(UpdateCommand.class, this::onUpdate);
    }

    private void onUpdate(AppRequest appRequest) {
        UpdateCommand req = (UpdateCommand) appRequest.payload();
        dao.update(req.id, req.element);
    }
}
