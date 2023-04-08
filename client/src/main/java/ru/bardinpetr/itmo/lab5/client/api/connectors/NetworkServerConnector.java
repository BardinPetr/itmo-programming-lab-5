package ru.bardinpetr.itmo.lab5.client.api.connectors;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APIResponse;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;
import ru.bardinpetr.itmo.lab5.network.client.APIClientController;

public class NetworkServerConnector implements APIClientReceiver {

    private final APIClientController apiController;

    public NetworkServerConnector(APIClientController apiController) {
        this.apiController = apiController;
    }

    @Override
    public APIResponse<APICommandResponse> call(APICommand cmd) {
        return apiController.call(new AppRequest<>(cmd));
    }
}
