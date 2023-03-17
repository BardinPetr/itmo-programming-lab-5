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
    final UIReceiver uiReceiver;
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
        uiReceiver.display("> ");
        while (uiReceiver.hasNextLine()) {
            String[] userArgs = uiReceiver.nextLine().split(" ");
            String commandName = userArgs[0];

            var command = (UILocalCommand) registryCommand.getCommand(commandName);
            command.executeWithArgs(List.of(userArgs));
        }
    }
}
