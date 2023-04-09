package ru.bardinpetr.itmo.lab5.network.app;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.commands.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddCommand;
import ru.bardinpetr.itmo.lab5.network.app.interfaces.DestinationServerApplication;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.models.AppResponse;
import ru.bardinpetr.itmo.lab5.network.app.special.SourcingServerApplication;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.session.models.Session;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class DemoUDPServerApplication extends SourcingServerApplication<SocketMessage> implements DestinationServerApplication {

    public DemoUDPServerApplication() {
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
    protected <K extends IIdentifiableCommand, R> AppRequest<K, R> buildRequestObject(SocketMessage sourceMessage) {
        var resp = new AppResponseController<>(new Random().nextLong(0, (long) 1e9), this);
        var session = new Session<>(1);
        var payload = new AddCommand();

        log.info("Initiating request {} from {}: {}", resp.getId(), 1, payload);

        return (AppRequest<K, R>) new AppRequest<>(session, resp, payload);
    }

    @Override
    public <T> void send(AppResponse<T> response) {
        log.info("Requested sending AppResponse: {}", response.toString());
    }
}
