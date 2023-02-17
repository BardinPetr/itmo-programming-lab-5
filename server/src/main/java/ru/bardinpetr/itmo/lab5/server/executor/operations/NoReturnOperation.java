package ru.bardinpetr.itmo.lab5.server.executor.operations;

import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

/**
 * Server side implementation of clients Command without response
 *
 * @param <T> Command to respond on
 */
public interface NoReturnOperation<T extends Command> {
    void apply(T cmd);
}
