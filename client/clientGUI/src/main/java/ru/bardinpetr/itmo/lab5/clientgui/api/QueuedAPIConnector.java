package ru.bardinpetr.itmo.lab5.clientgui.api;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIConnectorDecorator;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueuedAPIConnector extends APIConnectorDecorator {
    private final ExecutorService executor;

    public QueuedAPIConnector(APIClientConnector decoratee) {
        super(decoratee);
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public APICommandResponse call(APICommand cmd) throws APIClientException {
        try {
            return executor.submit(() -> {
                try {
                    return decoratee.call(cmd);
                } catch (APIClientException e) {
                    throw new RuntimeException(e);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new APIClientException(e);
        }
    }
}
