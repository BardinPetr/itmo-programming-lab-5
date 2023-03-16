package ru.bardinpetr.itmo.lab5.client.controller.comands;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.controller.comands.common.APILocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.comands.common.CommandResponse;
import ru.bardinpetr.itmo.lab5.client.tui.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.UpdateCommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

import java.util.Map;


public class UpdateAPILocalCommand extends APILocalCommand {

    public UpdateAPILocalCommand(APIClientReceiver api, UIReceiver ui) {
        super(api, ui);
    }

    @Override
    protected APICommand retriveAPICommand(String name) {
        return new UpdateCommand();
    }

    @Override
    protected APICommand prepareAPIMessage(String name, Map<String, Object> args) {
        return super.prepareAPIMessage(name, args);
    }

    @Override
    public CommandResponse execute(String cmdName, Map<String, Object> args) {
        return super.execute(cmdName, args);
    }
}
