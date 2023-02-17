package ru.bardinpetr.itmo.lab5.common.executor;

import ru.bardinpetr.itmo.lab5.models.commands.HelpCommand;

public class UIExecutor extends Executor {
    public void registerLocalCommands() {
        registerVoidOperation(HelpCommand.class, req -> {
        });
    }

}
