package ru.bardinpetr.itmo.lab5.common.executor.operations;

import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;

/**
 * Server side implementation of clients Command
 *
 * @param <T> Command to respond on
 * @param <V> Response class of function
 */
public interface Operation<T extends APICommand, V extends ICommandResponse> {
    V apply(T cmd);
}
