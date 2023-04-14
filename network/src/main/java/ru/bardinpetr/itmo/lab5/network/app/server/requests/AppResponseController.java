package ru.bardinpetr.itmo.lab5.network.app.server.requests;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APIResponseStatus;
import ru.bardinpetr.itmo.lab5.network.app.server.interfaces.types.IDestinationServerApplication;

/**
 * Object for handling the process of response building and sending it.
 * Message is marked as terminated when it is no need in continuation of its further processing
 *
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
        this.recipient = (U) request.session().getAddress();
        this.response = new APICommandResponse();
        this.destination = dst;
        this.id = request.id();
    }

    /**
     * Check if message is still could be processed and was not sent earlier
     */
    public boolean isTerminated() {
        return isTerminated;
    }

    /**
     * Mark response as terminated and prevent further processing
     */
    public void terminate() {
        log.debug("Message {} terminated", id);
        isTerminated = true;
    }

    /**
     * Set response status
     */
    public AppResponseController<U> status(APIResponseStatus status) {
        response.setStatus(status);
        log.debug("Response {} is marked as {}", id, status);
        return this;
    }

    /**
     * @return status of response
     */
    public APIResponseStatus getStatus() {
        return response.getStatus();
    }

    /**
     * Set response textual message
     */
    public AppResponseController<U> message(String text) {
        response.setTextualResponse(text);
        return this;
    }

    /**
     * Call IDestinationServerApplication to send prepared message.
     * Automatically terminates message
     */
    public void send() {
        if (isTerminated) return;
        terminate();

        if (response.getStatus() == APIResponseStatus.UNPROCESSED)
            response.setStatus(APIResponseStatus.OK);
        destination.send(recipient, response);
        log.debug("Response {} is sent", id);
    }

    /**
     * Set current APICommandResponse from external object
     *
     * @param resp prepared response object
     */
    public AppResponseController<U> from(APICommandResponse resp) {
        response = resp;
        return this;
    }

    /**
     * @return response id
     */
    public Long getId() {
        return id;
    }
}
