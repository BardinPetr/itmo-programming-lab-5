package ru.bardinpetr.itmo.lab5.client.api.connectors;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.Response;

/**
 * Allows to connect server executor as backend provider in client without using any communication
 */
public class LocalExecutorAPIConnector implements APIClientReceiver {
    private final Executor currentExecutor;

    public LocalExecutorAPIConnector(Executor currentExecutor) {
        this.currentExecutor = currentExecutor;
    }

    @Override
    public Response<APICommandResponse> call(APICommand cmd) {
        return currentExecutor.execute(cmd);
    }
}
