package ru.bardinpetr.itmo.lab5.network.app;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.network.app.interfaces.types.IDestinationServerApplication;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.models.AppResponse;

/**
 * @param <U> user identifier
 * @param <T> response base type
 */
@Slf4j
public class AppResponseController<U, T> {

    private final IDestinationServerApplication<U, T> destination;
    private final AppResponse<U, T> response;
    private final Long id;
    private boolean isTerminated = false;

    public AppResponseController(AppRequest<?, T> request, IDestinationServerApplication<U, T> dst) {
        @SuppressWarnings("unchecked")
        var recipient = (U) request.getSession().getAddress();
        this.response = new AppResponse<>(recipient);
        this.destination = dst;
        this.id = request.getId();
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
        isTerminated = true;
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
