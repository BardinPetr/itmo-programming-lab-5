package ru.bardinpetr.itmo.lab5.network.app.special.impl;

import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.APIApplication;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.models.AppResponse;

/**
 * Application for terminating any requests that was not terminated by other applications or requests that are invalid
 */
public class ErrorHandlingApplication extends APIApplication {
    @Override
    protected AppRequest<APICommand, APICommandResponse> process(AppRequest<APICommand, APICommandResponse> request) {
        var resp = request.getResponse();
        resp.status(AppResponse.ResponseStatus.ERROR);
        resp.end();
        return request;
    }

    /**
     * Allows requests only
     */
    @Override
    public boolean filter(AppRequest<APICommand, APICommandResponse> req) {
        return req.getStatus() == AppRequest.ReqStatus.INVALID && !req.getResponse().isTerminated();
    }
}
