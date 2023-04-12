package ru.bardinpetr.itmo.lab5.network.app;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.interfaces.types.IDestinationServerApplication;
import ru.bardinpetr.itmo.lab5.network.app.requests.AppRequest;

/**
 * @param <U> user identifier
 */
@Slf4j
public class AppResponseController<U> {

    private final IDestinationServerApplication<U> destination;
    private final Long id;
    private final U recipient;
    private APICommandResponse response;
    private boolean isTerminated = false;

    public AppResponseController(AppRequest request, IDestinationServerApplication<U> dst) {
        this.recipient = (U) request.getSession().getAddress();
        this.response = new APICommandResponse();
        this.destination = dst;
        this.id = request.getId();
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void terminate() {
        isTerminated = true;
    }

    public AppResponseController<U> status(APICommandResponse.Status status) {
        response.setStatus(status);
        log.debug("Response {} is marked as {}", id, status);
        return this;
    }

    public AppResponseController<U> message(String text) {
        response.setTextualResponse(text);
        return this;
    }

    public void send() {
        if (isTerminated) return;
        isTerminated = true;
        destination.send(recipient, response);
        log.debug("Response {} is sent", id);
    }

    public AppResponseController<U> data(APICommandResponse resp) {
        response = resp;
        return this;
    }

    public Long getId() {
        return id;
    }
}
