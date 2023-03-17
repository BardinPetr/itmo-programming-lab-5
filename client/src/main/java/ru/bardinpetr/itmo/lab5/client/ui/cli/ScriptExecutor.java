package ru.bardinpetr.itmo.lab5.client.ui.cli;

import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.registry.CommandRegistry;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;

import java.util.List;
import java.util.Scanner;

public class ScriptExecutor {
    private CommandRegistry registryCommand;

    public void setRegistry(CommandRegistry commandRegistry) {
        this.registryCommand = commandRegistry;
    }

    public void process(String path) throws FileAccessException {
        if (registryCommand == null) throw new RuntimeException("No command registry");

        FileIOController fileIOController = new FileIOController(path);

        UIReceiver uiReceiver = new CLIController(
                ConsolePrinter.getStub(),
                fileIOController.openReadStream()
        );

        var currentRegistry = registryCommand.withUI(uiReceiver);
        while (uiReceiver.hasNextLine()) {
            var userArgs = uiReceiver.nextLine().split("\\s+");
            var command = (UILocalCommand) currentRegistry.getCommand(userArgs[0]);
            command.executeWithArgs(List.of(userArgs));
        }
    }
}
