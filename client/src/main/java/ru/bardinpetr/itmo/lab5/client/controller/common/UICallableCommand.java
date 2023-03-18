package ru.bardinpetr.itmo.lab5.client.controller.common;

import java.util.List;

/**
 *
 */
public interface UICallableCommand {

    /**
     * Call command from CLI. Arguments should be parsed the way to call execute() then
     *
     * @param args list of arguments including command name as args[0]
     * @return result of execution
     */
    CommandResponse executeWithArgs(List<String> args);
}
