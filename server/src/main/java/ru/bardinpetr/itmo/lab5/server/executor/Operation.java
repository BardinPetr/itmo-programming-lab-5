package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.models.commands.Command;
import ru.bardinpetr.itmo.lab5.models.resonses.Response;

/**
 * Server side implementation of clients Command
 *
 * @param <T> Command to respond on
 * @param <V> Response class of function
 */
public interface Operation<T extends Command, V extends Response> {
    V apply(T cmd);
}
