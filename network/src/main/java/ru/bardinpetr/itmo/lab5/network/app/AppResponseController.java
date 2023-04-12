package ru.bardinpetr.itmo.lab5.network.app;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.network.app.interfaces.types.DestinationServerApplication;
import ru.bardinpetr.itmo.lab5.network.app.models.AppResponse;

/**
 * @param <T> response base type
 */
@Slf4j
public class AppResponseController<T> {

    private final DestinationServerApplication destination;
    private final AppResponse<T> response;
    private final Long id;
    private boolean isTerminated = false;

    public AppResponseController(Long id, DestinationServerApplication dst) {
        this.id = id;
        this.response = new AppResponse<>();
        this.destination = dst;
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void terminate() {
        isTerminated = true;
    }

    public void status(AppResponse.ResponseStatus status) {
        response.setStatus(status);
        log.debug("Response {} is marked as {}", id, status);
    }

    public void end() {
        if (isTerminated) return;
        destination.send(response);
        log.debug("Response {} is sent", id);
    }

    public void data(T payload) {
        response.setPayload(payload);
    }

    public void send(T payload) {
        if (response.getStatus() == AppResponse.ResponseStatus.WAITING)
            status(AppResponse.ResponseStatus.OK);
        data(payload);
        end();
    }

    public Long getId() {
        return id;
    }
}
