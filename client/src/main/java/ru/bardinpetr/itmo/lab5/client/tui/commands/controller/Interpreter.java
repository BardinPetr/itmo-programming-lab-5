package ru.bardinpetr.itmo.lab5.client.tui.commands.controller;

import ru.bardinpetr.itmo.lab5.client.controller.commands.IRRegistryCommand;
import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.tui.cli.CLIUtilityController;
import ru.bardinpetr.itmo.lab5.client.tui.cli.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.tui.cli.UIReceiver;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for console UI
 */
public class Interpreter {
    UIReceiver uiReceiver;
    ConsolePrinter printer;
    Scanner scanner;
    private IRRegistryCommand registryCommand;

    public Interpreter(ConsolePrinter printer, InputStream inputStream) {
        this.printer = printer;
        this.scanner = new Scanner(inputStream);
        uiReceiver = new CLIUtilityController(printer, scanner);
    }

    public Interpreter(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        uiReceiver = new CLIUtilityController(printer, scanner);
    }


    /**
     * method for starting program execution
     */
    public void run() {
        printer.display(RussianText.get(TextKeys.GREEETING));
        printer.suggestInput();
        while (scanner.hasNextLine()) {
            String[] userArgs = scanner.nextLine().split(" ");
            String commandName = userArgs[0];

            UILocalCommand command = null;
            command = registryCommand.getByName(commandName);
            command.executeWithArgs(new ArrayList<>(List.of(userArgs))); //TODO delete first arg

        }
    }
}
