package ru.bardinpetr.itmo.lab5.network.app.special;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.commands.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.ServerApplication;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;

/**
 * Server application acting as source of AppRequests
 *
 * @param <T> low-level message object to be used as source for AppRequest
 */
@Slf4j
public abstract class SourcingServerApplication<T> extends ServerApplication {

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
     * @param <K>           type of AppRequest payload
     * @param <R>           response type
     * @return prepared AppRequest
     */
    protected abstract <K extends IIdentifiableCommand, R> AppRequest<K, R> buildRequestObject(T sourceMessage);
}