package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.*;
import ru.bardinpetr.itmo.lab5.server.dao.workers.IWorkerCollectionDAO;

import java.util.Comparator;

/**
 * Executor for resolving workers collection commands to DAO calls
 */
public class WorkersDAOExecutor extends Executor {

    private final IWorkerCollectionDAO dao;

    public WorkersDAOExecutor(IWorkerCollectionDAO dao) {
        this.dao = dao;
        registerCRUD();
        registerComplex();
    }

    private void registerCRUD() {
        registerOperation(InfoCommand.class, req -> {
            var resp = req.createResponse();
            resp.setResult(dao.getCollectionInfo());
            return resp;
        });
        registerOperation(ShowCommand.class, req -> {
            var resp = req.createResponse();
            resp.setResult(dao.readAll());
            return resp;
        });
        registerVoidOperation(
                AddCommand.class,
                req -> dao.add(req.element)
        );
        registerVoidOperation(
                UpdateCommand.class,
                req -> dao.update(req.id, req.element)
        );
        registerVoidOperation(
                RemoveByIdCommand.class,
                req -> dao.remove(req.id)
        );
        registerVoidOperation(
                ClearCommand.class,
                req -> dao.clear()
        );
        registerVoidOperation(
                SaveCommand.class,
                req -> dao.save()
        );
    }

    private void registerComplex() {
        registerVoidOperation(
                AddIfMaxCommand.class,
                req -> dao.addIfMax(req.element)
        );
        registerVoidOperation(
                AddIfMinCommand.class,
                req -> dao.addIfMin(req.element)
        );
        registerVoidOperation(
                RemoveGreaterCommand.class,
                req -> dao.removeIfGreater(req.element)
        );
        registerOperation(
                PrintDescendingCommand.class,
                req -> {
                    var resp = req.createResponse();
                    resp.setResult(dao.readAll(Comparator.naturalOrder()));
                    return resp;
                }
        );
        registerOperation(
                UniqueOrganisationCommand.class,
                req -> {
                    var resp = req.createResponse();
                    resp.setOrganizations(dao.getUniqueOrganizations());
                    return resp;
                }
        );
        registerOperation(
                FilterLessPosCommand.class,
                req -> {
                    var resp = req.createResponse();
                    resp.setResult(dao.filterLessThanPosition(req.position));
                    return resp;
                }
        );
    }
}
