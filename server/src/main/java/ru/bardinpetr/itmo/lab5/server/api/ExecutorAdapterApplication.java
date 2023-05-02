package ru.bardinpetr.itmo.lab5.server.api;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.server.interfaces.handlers.IApplicationCommandHandler;
import ru.bardinpetr.itmo.lab5.network.app.server.models.requests.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.APIApplication;

/**
 * API Application to forward all messages to Executor instance.
 * Request commands are meant to be identical to App and Exector.
 */
@Slf4j
public class ExecutorAdapterApplication extends APIApplication implements IApplicationCommandHandler {

    private final Executor target;

    public ExecutorAdapterApplication(Executor target) {
        this.target = target;
        on(this);
    }

    @Override
    public void handle(AppRequest request) {
        APICommand command = request.payload();
        log.debug("DBE: New request from {}: {}", request.session().getAddress(), request);

        APICommandResponse resp = target.execute(command);
        request.response()
                .from(resp)
                .send();
    }
}
