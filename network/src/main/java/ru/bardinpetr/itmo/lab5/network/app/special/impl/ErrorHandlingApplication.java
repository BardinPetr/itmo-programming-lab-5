package ru.bardinpetr.itmo.lab5.network.app.special.impl;

import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.APIApplication;
import ru.bardinpetr.itmo.lab5.network.app.requests.AppRequest;

/**
 * Application for terminating any requests that was not terminated by other applications or requests that are invalid
 */
public class ErrorHandlingApplication extends APIApplication {
    @Override
    protected AppRequest process(AppRequest request) {
        var resp = request.getResponse();
        resp.status(APICommandResponse.Status.CLIENT_ERROR);
        resp.send();
        return request;
    }

    /**
     * Allows requests only
     */
    @Override
    public boolean filter(AppRequest req) {
        return req.getStatus() == AppRequest.ReqStatus.INVALID && !req.getResponse().isTerminated();
    }
}
