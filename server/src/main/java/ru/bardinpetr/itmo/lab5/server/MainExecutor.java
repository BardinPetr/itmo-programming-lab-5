package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.ExecuteScriptCommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.dao.workers.FileDBWorkersDAO;
import ru.bardinpetr.itmo.lab5.server.executor.WorkersDAOExecutor;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;

public class MainExecutor extends Executor {

    public MainExecutor(FileDBController<WorkerCollection> dbController) {
        var dao = new FileDBWorkersDAO(dbController);
        registerExecutor(new WorkersDAOExecutor(dao));

        registerOperation(ExecuteScriptCommand.class, this::processScript);
    }

    private ICommandResponse processScript(ExecuteScriptCommand req) {
        var resp = req.createResponse();
        var commands = req.getCommands();
        if (commands.isEmpty()) throw new RuntimeException("No commands");
        resp.setResult(executeBatch(commands));
        return resp;
    }
}
