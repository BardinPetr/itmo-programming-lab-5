package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.*;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.server.dao.workers.IWorkerCollectionDAO;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Executor for resolving workers collection commands to DAO calls
 */
public class WorkersSpecialExecutor extends Executor {

    private final IWorkerCollectionDAO dao;

    public WorkersSpecialExecutor(IWorkerCollectionDAO dao) {
        this.dao = dao;

        registerVoidOperation(AddIfMaxCommand.class, this::addIfMax);
        registerVoidOperation(AddIfMinCommand.class, this::addIfMin);
        registerVoidOperation(RemoveGreaterCommand.class, this::removeIfGreater);
        registerOperation(PrintDescendingCommand.class, this::printDescending);
        registerOperation(UniqueOrganisationCommand.class, this::uniqueOrgs);
        registerOperation(FilterLessPosCommand.class, this::filterLess);
    }

    private FilterLessPosCommand.FilterLessPosCommandResponse filterLess(FilterLessPosCommand req) {
        var data = dao.filter(worker -> worker.getPosition().compareTo(req.position) < 0);
        var resp = req.createResponse();
        resp.setResult(data);
        return resp;
    }

    private void removeIfGreater(RemoveGreaterCommand req) {
        dao.remove(cur -> cur.compareTo(req.element) > 0);
    }

    private PrintDescendingCommand.PrintDescendingCommandResponse printDescending(PrintDescendingCommand req) {
        var resp = req.createResponse();
        resp.setResult(dao.readAll(Comparator.reverseOrder()));
        return resp;
    }

    private UniqueOrganisationCommand.UniqueOrganisationCommandResponse uniqueOrgs(UniqueOrganisationCommand req) {
        var resp = req.createResponse();
        resp.setOrganizations(dao.readAll().stream().map(Worker::getOrganization).collect(Collectors.toSet()));
        return resp;
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
