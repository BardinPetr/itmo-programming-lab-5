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
        var scanner = new Scanner(fileIOController.openReadStream());

        UIReceiver uiReceiver = new CLIController(ConsolePrinter.getStub(), scanner);
        var currentRegistry = registryCommand.withUI(uiReceiver);
        while (scanner.hasNextLine()) {
            String[] userArgs = scanner.nextLine().split("\s+");
            String commandName = userArgs[0];

            var command = (UILocalCommand) currentRegistry.getCommand(commandName);
            command.executeWithArgs(List.of(userArgs));
        }
    }
}
