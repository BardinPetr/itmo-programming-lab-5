package ru.bardinpetr.itmo.lab5.client.api.connectors;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.client.APIClientController;

public class NetworkServerConnector implements APIClientReceiver {

    private final APIClientController apiController;

    public NetworkServerConnector(APIClientController apiController) {
        this.apiController = apiController;
    }

    @Override
    public APICommandResponse call(APICommand cmd) {
        return apiController.call(cmd);
    }
}
