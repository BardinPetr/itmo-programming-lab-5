package ru.bardinpetr.itmo.lab5.client.tui.commands.controller;

import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.tui.cli.CLIUtilityController;
import ru.bardinpetr.itmo.lab5.client.tui.cli.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.tui.cli.UIReceiver;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for console UI
 */
public class CLIController {
    UIReceiver uiReceiver;
    ConsolePrinter printer = new ConsolePrinter();
    Scanner scanner;

    public CLIController(ConsolePrinter printer, InputStream inputStream) {
        uiReceiver = new CLIUtilityController(printer, new Scanner(inputStream));
        scanner = new Scanner(inputStream);
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

//            command = (UILocalCommand) (CommandRegistry.getInstance()).getByName(commandName);
//            command.executeWithArgs(new ArrayList<>(List.of(userArgs))); //TODO delete first arg

        }
    }
}
