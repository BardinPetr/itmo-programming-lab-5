package ru.bardinpetr.itmo.lab5.server.app.modules.db;

import ru.bardinpetr.itmo.lab5.db.frontend.adapters.owned.IOwnedCollectionDAO;
import ru.bardinpetr.itmo.lab5.models.commands.api.UpdateCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.UpdateOrgCommand;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.network.app.server.models.requests.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.APIApplication;

import static ru.bardinpetr.itmo.lab5.server.app.utils.AppUtils.extractUser;

public class DBUpdateApplication extends APIApplication {
    private final IOwnedCollectionDAO<Integer, Worker> dao;

    public DBUpdateApplication(IOwnedCollectionDAO<Integer, Worker> dao) {
        this.dao = dao;
        on(UpdateCommand.class, this::onUpdate);
        on(UpdateOrgCommand.class, this::onUpdateOrg);
    }

    private void onUpdate(AppRequest request) {
        UpdateCommand req = (UpdateCommand) request.payload();
        if (dao.update(extractUser(request), req.id, req.element))
            request.response().sendOk();
        else
            request.response().sendErr("DBUpdateApplication.error.text");
    }

    private void onUpdateOrg(AppRequest request) {
        UpdateOrgCommand req = (UpdateOrgCommand) request.payload();
        if (dao.delOrg(req.id))
            request.response().sendOk();
        else
            request.response().sendErr("DBUpdateApplication.error.text");
    }
}
