package ru.bardinpetr.itmo.lab5.server.api;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APIResponse;
import ru.bardinpetr.itmo.lab5.network.app.ServerApplication;
import ru.bardinpetr.itmo.lab5.network.app.interfaces.IApplicationCommandHandler;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;

import static ru.bardinpetr.itmo.lab5.network.app.models.AppResponse.ResponseStatus;

@Slf4j
public class ExecutorAdapterApplication extends ServerApplication implements IApplicationCommandHandler<APICommand, APIResponse> {

    private final Executor target;

    public ExecutorAdapterApplication(Executor target) {
        this.target = target;
        on(this);
    }

    @Override
    public void handle(AppRequest<APICommand, APIResponse> request) {
        APICommand command = request.cmd();
        log.debug("DBE: New request from {}: {}", request.session().getAddress(), request);

        APIResponse<APICommandResponse> resp = target.execute(command);

        if (resp.isSuccess()) {
            request.response().status(ResponseStatus.OK);
        } else if (!resp.isResolved()) {
            request.response().status(ResponseStatus.SERVER_ERROR);
        } else {
            request.response().status(ResponseStatus.CLIENT_ERROR);
        }

        request.response().send(resp);
    }
}