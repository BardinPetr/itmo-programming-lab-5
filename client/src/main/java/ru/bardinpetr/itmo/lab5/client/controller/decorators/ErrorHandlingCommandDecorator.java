package ru.bardinpetr.itmo.lab5.client.controller.decorators;

import ru.bardinpetr.itmo.lab5.client.controller.common.AbstractCommandDecorator;
import ru.bardinpetr.itmo.lab5.client.controller.common.CommandResponse;
import ru.bardinpetr.itmo.lab5.client.controller.common.UICallableCommand;
import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ScriptException;

import java.util.List;
import java.util.Map;

public class ErrorHandlingCommandDecorator extends AbstractCommandDecorator implements UICallableCommand {
    public ErrorHandlingCommandDecorator(UILocalCommand decoratee) {
        super(decoratee);
    }

    @Override
    public CommandResponse execute(String cmdName, Map<String, Object> args) {
        try {
            return decoratee.execute(cmdName, args);
        } catch (ScriptException ex) {
            throw ex;
        } catch (Exception ex) {
            return CommandResponse.error(ex.getMessage());
        }
    }

    @Override
    public void executeWithArgs(List<String> args) {
        ((UILocalCommand) decoratee).executeWithArgs(args);
    }
}
