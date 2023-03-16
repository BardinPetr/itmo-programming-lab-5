package ru.bardinpetr.itmo.lab5.client.tui;

import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.Response;

/**
 * Interface for commands execution
 */
@FunctionalInterface
public interface ICommandIOCallback {
    Response<ICommandResponse> callback(APICommand cmd);
}
