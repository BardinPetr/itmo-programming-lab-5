package ru.bardinpetr.itmo.lab5.network.app;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddCommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.interfaces.DestinationServerApplication;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.models.AppResponse;
import ru.bardinpetr.itmo.lab5.network.app.special.TransportAPIApplication;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.session.models.Session;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class DemoUDPAPIApplication extends TransportAPIApplication<APICommand, APICommandResponse, SocketMessage> implements DestinationServerApplication<APICommand> {

    public DemoUDPAPIApplication() {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        initiate(null);
                    }
                },
                0,
                100000
        );
    }

    @Override
    protected AppRequest<APICommand, APICommandResponse> buildRequestObject(SocketMessage sourceMessage) {
        var resp = new AppResponseController<APICommandResponse>(new Random().nextLong(0, (long) 1e9), this);
        var session = new Session<>(1);
        var payload = new AddCommand();

        log.info("Initiating request {} from {}: {}", resp.getId(), 1, payload);

        return new AppRequest<>(false, session, resp, payload);
    }

    @Override
    public void send(AppResponse<APICommand> response) {
        log.info("Requested sending AppResponse: {}", response.toString());
    }
}
