package ru.bardinpetr.itmo.lab5.client.controller.commands;

import ru.bardinpetr.itmo.lab5.client.controller.common.CommandResponse;
import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.tui.cli.UIReceiver;

import java.util.Map;


public class HelpLocalCommand extends UILocalCommand {

    public HelpLocalCommand(UIReceiver ui) {
        super(ui);
    }

    @Override
    public CommandResponse execute(String cmdName, Map<String, Object> args) {
        uiReceiver.display(RussianText.get(TextKeys.HELP));
        return CommandResponse.ok();
    }
}
