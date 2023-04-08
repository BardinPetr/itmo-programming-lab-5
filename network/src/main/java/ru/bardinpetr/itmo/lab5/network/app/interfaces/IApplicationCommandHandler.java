package ru.bardinpetr.itmo.lab5.network.app.interfaces;

import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.models.AppResponse;

/**
 * @param <S> session user identifier type
 * @param <K> request payload type
 * @param <V> response payload type
 */
public interface IApplicationCommandHandler<S, K, V> {
    AppResponse<V> handle(AppRequest<S, K> request);
}
