package ru.bardinpetr.itmo.lab5.client.tui.commands.controller;

import ru.bardinpetr.itmo.lab5.client.controller.commands.IRRegistryCommand;
import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.tui.Printer;
import ru.bardinpetr.itmo.lab5.client.tui.newThings.CLIUtilityController;
import ru.bardinpetr.itmo.lab5.client.tui.newThings.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.tui.newThings.ConsoleReader;
import ru.bardinpetr.itmo.lab5.client.tui.newThings.UIReceiver;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for console UI
 */
public class CLIController{
    UIReceiver uiReceiver;
    ConsolePrinter printer = new ConsolePrinter();
    ConsoleReader reader = new ConsoleReader();
    private IRRegistryCommand registryCommand;
    public CLIController(Printer viewer, InputStream inputStream) {
        uiReceiver = new CLIUtilityController();
    }


    /**
     * method for starting program execution
     */
    public void run() {
        printer.display(RussianText.get(TextKeys.GREEETING));
        printer.suggestInput();
        while (reader.hasNextLine()) {
            String[] userArgs = reader.readLine().split(" ");
            String commandName = userArgs[0];

            UILocalCommand command = null;
            command = (UILocalCommand) registryCommand.getByName(commandName);
            command.executeWithArgs(new ArrayList<>(List.of(userArgs))); //TODO delete first arg

        }
    }
}
