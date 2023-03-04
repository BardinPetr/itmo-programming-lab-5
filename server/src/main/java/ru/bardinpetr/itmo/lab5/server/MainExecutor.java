package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.models.commands.LocalExecuteScriptCommand;
import ru.bardinpetr.itmo.lab5.models.commands.ServerExecuteScriptCommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.dao.workers.FileDBWorkersDAO;
import ru.bardinpetr.itmo.lab5.server.executor.WorkersDAOExecutor;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;

public class MainExecutor extends Executor {

    public MainExecutor(FileIOController fileIOController) {
        FileDBController<WorkerCollection> db;
        try {
            db = new FileDBController<>(fileIOController, WorkerCollection.class);
        } catch (FileAccessException e) {
            System.err.printf("[DB] could not read from file. Fix by hand please. \nError: %s", e);
            System.exit(1);
            return;
        }

        var dao = new FileDBWorkersDAO(db);
        registerExecutor(new WorkersDAOExecutor(dao));

        registerOperation(ServerExecuteScriptCommand.class, this::processScript);
        registerOperation(LocalExecuteScriptCommand.LocalExecuteScriptCommandResponse.class, this::processScript);
    }

    private ICommandResponse processScript(ServerExecuteScriptCommand req) {
        var resp = req.createResponse();
        var commands = req.getCommands();
        if (commands.isEmpty()) throw new RuntimeException("No commands");
        resp.setResult(executeBatch(commands));
        return resp;
    }
}
