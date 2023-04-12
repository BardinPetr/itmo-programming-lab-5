package ru.bardinpetr.itmo.lab5.network.client;

import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

import java.net.InetSocketAddress;

@Deprecated(forRemoval = true)
public class APIClientController {

    public APIClientController(InetSocketAddress server) {

    }

    public APICommandResponse call(APICommand request) {
        return null;
    }
}
