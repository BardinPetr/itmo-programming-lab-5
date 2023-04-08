package ru.bardinpetr.itmo.lab5.client.api;

import ru.bardinpetr.itmo.lab5.models.commands.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APIResponse;

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
    APIResponse<APICommandResponse> call(APICommand cmd);
}
