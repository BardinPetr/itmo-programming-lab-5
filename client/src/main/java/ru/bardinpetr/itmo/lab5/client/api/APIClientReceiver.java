package ru.bardinpetr.itmo.lab5.client.api;

import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.Response;

/**
 * Interface to call server via APICommand interface
 */
public interface APIClientReceiver {

    /**
     * Send APICommand to server and get result
     *
     * @param cmd command to execute on server
     * @return Response with ICommandResponse from execution
     */
    Response<ICommandResponse> call(APICommand cmd);
}
