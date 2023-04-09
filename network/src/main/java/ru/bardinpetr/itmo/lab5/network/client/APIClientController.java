package ru.bardinpetr.itmo.lab5.network.client;

import ru.bardinpetr.itmo.lab5.models.commands.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APIResponse;

import java.net.InetSocketAddress;

public class APIClientController {

    public APIClientController(InetSocketAddress server) {

    }

    public APIResponse<APICommandResponse> call(APICommand request) {
        return null;
    }
}
