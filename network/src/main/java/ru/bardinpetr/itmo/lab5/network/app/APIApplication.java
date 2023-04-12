package ru.bardinpetr.itmo.lab5.network.app;

import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;

/**
 * Base application for writing API handlers.
 * Pre-configured to answer valid requests.
 */
public class APIApplication extends AbstractApplication<APICommand, APICommandResponse> {

    @Override
    public boolean filter(AppRequest<APICommand, APICommandResponse> req) {
        return req.getStatus() == AppRequest.ReqStatus.NORMAL;
    }
}
