package ru.bardinpetr.itmo.lab5.client.api.connectors;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.Response;

public class LocalExecutorAPIConnector implements APIClientReceiver {

    private final Executor currentExecutor;

    public LocalExecutorAPIConnector(Executor currentExecutor) {
        this.currentExecutor = currentExecutor;
    }

    @Override
    public Response<ICommandResponse> call(Command cmd) {
        return currentExecutor.execute(cmd);
    }
}
