package ru.bardinpetr.itmo.lab5.client.api.connectors;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.client.impl.SocketAPIClient;

import java.io.IOException;

public class NetworkServerConnector implements APIClientReceiver {

    private final SocketAPIClient apiController;

    public NetworkServerConnector(SocketAPIClient apiController) {
        this.apiController = apiController;
    }

    @Override
    public APICommandResponse call(APICommand cmd) throws APIClientException {
        try {
            return apiController.request(cmd);
        } catch (IOException e) {
            throw new APIClientException(e);
        }
    }
}
