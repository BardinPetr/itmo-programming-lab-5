package ru.bardinpetr.itmo.lab5.server.api;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.APIApplication;
import ru.bardinpetr.itmo.lab5.network.app.interfaces.handlers.IApplicationCommandHandler;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;

import static ru.bardinpetr.itmo.lab5.network.app.models.AppResponse.ResponseStatus;

@Slf4j
public class ExecutorAdapterApplication extends APIApplication implements IApplicationCommandHandler<APICommand, APICommandResponse> {

    private final Executor target;

    public ExecutorAdapterApplication(Executor target) {
        this.target = target;
        on(this);
    }

    @Override
    public void handle(AppRequest<APICommand, APICommandResponse> request) {
        APICommand command = request.getPayload();
        log.debug("DBE: New request from {}: {}", request.getSession().getAddress(), request);

        APICommandResponse resp = target.execute(command);

        if (resp.isSuccess()) {
            request.getResponse().status(ResponseStatus.OK);
        } else {
            request.getResponse().status(ResponseStatus.ERROR);
        }

        request.getResponse().send(resp);
    }
}
