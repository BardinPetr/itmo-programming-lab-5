package ru.bardinpetr.itmo.lab5.client.ui.cli;

import ru.bardinpetr.itmo.lab5.client.controller.common.handlers.UICallableCommand;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ScriptException;

import java.util.List;

public interface IInvoker {
    boolean invoke(UICallableCommand command, List<String> args) throws ScriptException;
}
