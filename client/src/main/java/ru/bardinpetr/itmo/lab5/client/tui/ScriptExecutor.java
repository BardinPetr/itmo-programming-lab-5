package ru.bardinpetr.itmo.lab5.client.tui;

import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.registry.CommandRegistry;
import ru.bardinpetr.itmo.lab5.client.tui.cli.CLIUtilityController;
import ru.bardinpetr.itmo.lab5.client.tui.cli.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.tui.cli.UIReceiver;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;

import java.util.ArrayList;
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

        UIReceiver uiReceiver = new CLIUtilityController(ConsolePrinter.getStub(), scanner);

        while (scanner.hasNextLine()) {
            String[] userArgs = scanner.nextLine().split("\s+");
            String commandName = userArgs[0];

            var command = (UILocalCommand) registryCommand.getCommand(commandName);
            command.executeWithArgs(new ArrayList<>(List.of(userArgs))); //TODO delete first arg
        }
    }
}
