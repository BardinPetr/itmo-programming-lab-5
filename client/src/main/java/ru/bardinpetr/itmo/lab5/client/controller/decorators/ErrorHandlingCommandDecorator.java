package ru.bardinpetr.itmo.lab5.client.controller.decorators;

import ru.bardinpetr.itmo.lab5.client.controller.common.AbstractCommandDecorator;
import ru.bardinpetr.itmo.lab5.client.controller.common.AbstractLocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.common.CommandResponse;

import java.util.Map;

public class ErrorHandlingCommandDecorator extends AbstractCommandDecorator {
    public ErrorHandlingCommandDecorator(AbstractLocalCommand decoratee) {
        super(decoratee);
    }

    @Override
    public CommandResponse execute(String cmdName, Map<String, Object> args) {
        try {
            return decoratee.execute(cmdName, args);
        } catch (Exception ex) {
            return CommandResponse.error(ex.getMessage());
        }
    }
}
