package ru.bardinpetr.itmo.lab5.client.ui.cli;

import ru.bardinpetr.itmo.lab5.client.controller.common.UICallableCommand;
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
        uiReceiver.display("> ");
        while (uiReceiver.hasNextLine()) {
            var line = uiReceiver.nextLine();
            if (line == null) System.exit(0);

            var userArgs = line.split("\\s+");
            var command = (UICallableCommand) registryCommand.getCommand(userArgs[0]);
            if (command == null) {
                uiReceiver.display("Command not found");
                continue;
            }

            command.executeWithArgs(List.of(userArgs));
        }
    }
}
