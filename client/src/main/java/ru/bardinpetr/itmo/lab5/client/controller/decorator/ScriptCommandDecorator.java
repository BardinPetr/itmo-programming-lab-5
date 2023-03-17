package ru.bardinpetr.itmo.lab5.client.controller.decorator;

import ru.bardinpetr.itmo.lab5.client.controller.common.APILocalCommand;

public class ScriptCommandDecorator extends AbstractCommandDecorator {

    public ScriptCommandDecorator(APILocalCommand decoratee) {
        super(decoratee);
    }


}
