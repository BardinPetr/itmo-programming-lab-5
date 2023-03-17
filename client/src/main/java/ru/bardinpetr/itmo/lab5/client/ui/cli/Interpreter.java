package ru.bardinpetr.itmo.lab5.client.ui.cli;

import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.registry.CommandRegistry;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;

import java.util.List;

/**
 * Class for console UI
 */
public class Interpreter {
    private final UIReceiver uiReceiver;
    private final CommandRegistry registryCommand;

    public Interpreter(CommandRegistry registryCommand, UIReceiver uiReceiver) {
        this.registryCommand = registryCommand;
        this.uiReceiver = uiReceiver;
    }

    /**
     * method for starting program execution
     */
    public void run() {
        uiReceiver.display(RussianText.get(TextKeys.GREEETING));
        uiReceiver.interactSuggestion();
        while (uiReceiver.hasNextLine()) {
            var userArgs = uiReceiver.nextLine().split("\\s+");
            var command = (UILocalCommand) registryCommand.getCommand(userArgs[0]);
            command.executeWithArgs(List.of(userArgs));

            uiReceiver.interactSuggestion();
        }
    }
}
