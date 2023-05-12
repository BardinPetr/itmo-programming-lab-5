package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.models.commands.api.ClearCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.RemoveByIdCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.RemoveGreaterCommand;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.network.app.server.models.requests.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.APIApplication;
import ru.bardinpetr.itmo.lab5.server.dao.sync.IOwnedCollectionDAO;

import static ru.bardinpetr.itmo.lab5.server.dao.utils.AppUtils.extractUser;

public class DBRemoveApplication extends APIApplication {

    private final IOwnedCollectionDAO<Integer, Worker> dao;

    public DBRemoveApplication(IOwnedCollectionDAO<Integer, Worker> dao) {
        this.dao = dao;

        on(RemoveGreaterCommand.class, this::onRemoveGreater);
        on(RemoveByIdCommand.class, this::onRemove);
        on(ClearCommand.class, this::onClear);
    }

    private void onRemove(AppRequest appRequest) {
        RemoveByIdCommand req = (RemoveByIdCommand) appRequest.payload();
        boolean ok = dao.remove(extractUser(appRequest), req.id);

        if (ok)
            appRequest.response().sendOk();
        else
            appRequest.response().sendErr("Not found entity to remove");
    }

    private void onRemoveGreater(AppRequest appRequest) {
        RemoveGreaterCommand req = (RemoveGreaterCommand) appRequest.payload();

        dao
                .filter(cur -> cur.compareTo(req.element) > 0)
                .forEach(obj -> dao.remove(extractUser(appRequest), obj.getPrimaryKey()));

        appRequest.response().sendOk();
    }

    private void onClear(AppRequest appRequest) {
        dao.clear();

        appRequest.response().sendOk();
    }
}


