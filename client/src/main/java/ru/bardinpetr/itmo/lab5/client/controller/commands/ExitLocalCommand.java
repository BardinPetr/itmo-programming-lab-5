package ru.bardinpetr.itmo.lab5.client.controller.commands;

import ru.bardinpetr.itmo.lab5.client.controller.common.CommandResponse;
import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.tui.UIReceiver;

import java.util.Map;


public class ExitLocalCommand extends UILocalCommand {

    public ExitLocalCommand(UIReceiver ui) {
        super(ui);
    }

    @Override
    public String getExternalName() {
        return "exit";
    }

    @Override
    public CommandResponse execute(String cmdName, Map<String, Object> args) {
        System.exit(0);
        return CommandResponse.ok();
    }
}
