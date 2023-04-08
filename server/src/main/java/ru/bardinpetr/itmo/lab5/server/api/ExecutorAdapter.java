package ru.bardinpetr.itmo.lab5.server.api;

import ru.bardinpetr.itmo.lab5.models.commands.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APIResponse;
import ru.bardinpetr.itmo.lab5.network.app.interfaces.IApplicationCommandHandler;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.models.AppResponse;

import java.net.InetSocketAddress;
import java.util.function.Function;

public class ExecutorAdapter
        implements IApplicationCommandHandler<InetSocketAddress, APICommand, APIResponse<APICommandResponse>> {

    private final Function<APICommand, APIResponse<APICommandResponse>> target;

    public ExecutorAdapter(Function<APICommand, APIResponse<APICommandResponse>> target) {
        this.target = target;
    }

    @Override
    public AppResponse<APIResponse<APICommandResponse>> handle(AppRequest<InetSocketAddress, APICommand> request) {
        APICommand command = request.cmd();

        APIResponse<APICommandResponse> resp = target.apply(command);

        AppResponse.ResponseStatus status;
        if (resp.isSuccess()) {
            status = AppResponse.ResponseStatus.OK;
        } else if (!resp.isResolved()) {
            status = AppResponse.ResponseStatus.CLIENT_ERROR;
        } else {
            status = AppResponse.ResponseStatus.SERVER_ERROR;
        }

        return new AppResponse<>(status, resp);
    }
}
