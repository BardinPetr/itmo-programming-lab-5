package ru.bardinpetr.itmo.lab5.client.controller.commands;

import ru.bardinpetr.itmo.lab5.client.controller.common.ClientCommandResponse;
import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;

import java.util.Map;


/**
 * Command to print help
 */
public class HelpLocalCommand extends UILocalCommand {

    public HelpLocalCommand(UIReceiver ui) {
        super(ui);
    }

    @Override
    public String getExternalName() {
        return "help";
    }

    @Override
    public ClientCommandResponse execute(String cmdName, Map<String, Object> args) {
        return new ClientCommandResponse(true, RussianText.get(TextKeys.HELP), null);
    }
}
