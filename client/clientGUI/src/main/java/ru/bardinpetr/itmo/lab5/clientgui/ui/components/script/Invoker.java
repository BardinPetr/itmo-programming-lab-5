package ru.bardinpetr.itmo.lab5.clientgui.ui.components.script;

import ru.bardinpetr.itmo.lab5.client.controller.common.handlers.UICallableCommand;
import ru.bardinpetr.itmo.lab5.client.ui.cli.UICommandInvoker;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ScriptException;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;

import java.util.List;

public class Invoker extends UICommandInvoker {
    public Invoker() {
        super(null);
    }

    @Override
    public boolean invoke(UICallableCommand command, List<String> args) throws ScriptException {
        return true;
    }

    @Override
    public boolean invokeLast() {
        return true;
    }

}
