package ru.bardinpetr.itmo.lab5.network.app.models;

import ru.bardinpetr.itmo.lab5.network.session.models.Session;

/**
 * @param session
 * @param cmd
 * @param <K>     type of session user identifier
 * @param <T>     payload type
 */
public record AppRequest<K, T>(Session<K> session, T cmd) {
}
