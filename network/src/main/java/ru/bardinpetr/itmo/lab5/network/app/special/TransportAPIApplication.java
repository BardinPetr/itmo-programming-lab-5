package ru.bardinpetr.itmo.lab5.network.app.special;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.commands.requests.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.APIApplication;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;

/**
 * Server application acting as source of AppRequests
 *
 * @param <S> request base type
 * @param <R> response base type
 * @param <T> low-level message object to be used as source for AppRequest
 */
@Slf4j
public abstract class TransportAPIApplication<S extends IIdentifiableCommand, R, T> extends APIApplication<S, R> {

    /**
     * Method to be called when new message should be processed
     *
     * @param sourceMessage base message from underlying protocol
     */
    protected void initiate(T sourceMessage) {
        var request = buildRequestObject(sourceMessage);
        process(request);
    }

    /**
     * @param sourceMessage base message from underlying protocol
     * @return prepared AppRequest
     */
    protected abstract AppRequest<S, R> buildRequestObject(T sourceMessage);

//    public void send(AppRequest<S, R> ) {
//    }
}
