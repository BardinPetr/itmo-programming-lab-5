package ru.bardinpetr.itmo.lab5.client.controller.comands.decorator;

import ru.bardinpetr.itmo.lab5.client.controller.comands.common.AbstractLocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.comands.common.CommandResponse;

import java.util.Map;

public class AbstractCommandDecorator extends AbstractLocalCommand {
    protected final AbstractLocalCommand decoratee;

    public AbstractCommandDecorator(AbstractLocalCommand decoratee) {
        this.decoratee = decoratee;
    }

    @Override
    public CommandResponse execute(String cmdName, Map<String, Object> args) {
        return decoratee.execute(cmdName, args);
    }
}
