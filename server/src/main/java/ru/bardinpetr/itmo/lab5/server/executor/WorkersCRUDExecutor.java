package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.api.*;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.server.dao.workers.IWorkerCollectionDAO;

/**
 * Executor for resolving workers collection commands to DAO calls
 */
public class WorkersCRUDExecutor extends Executor {
    public WorkersCRUDExecutor(IWorkerCollectionDAO dao) {
        registerOperation(
                InfoCommand.class,
                req -> {
                    var resp = req.createResponse();
                    resp.setResult(dao.getCollectionInfo());
                    return resp;
                });
        registerOperation(
                GetWorkerCommand.class,
                req -> {
                    var resp = req.createResponse();
                    var worker = dao.read(req.getId());
                    if (worker == null)
                        throw new RuntimeException("No worker with id %d".formatted(req.getId()));
                    resp.setWorker(worker);
                    return resp;
                });
        registerOperation(
                GetWorkerIdsCommand.class,
                req -> {
                    var resp = req.createResponse();
                    resp.setResult(dao.getAllMapped(Worker::getId));
                    return resp;
                });
        registerOperation(
                AddCommand.class,
                req -> {
                    var resp = req.createResponse();
                    resp.setId(dao.add(req.element));
                    return resp;
                }
        );
        registerVoidOperation(
                UpdateCommand.class,
                req -> dao.update(req.id, req.element)
        );
        registerVoidOperation(
                RemoveByIdCommand.class,
                req -> {
                    if (!dao.remove(req.id))
                        throw new RuntimeException("Not found entity to remove");
                }
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
}
