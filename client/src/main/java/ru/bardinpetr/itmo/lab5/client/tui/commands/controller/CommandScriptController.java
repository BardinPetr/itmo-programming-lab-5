package ru.bardinpetr.itmo.lab5.client.tui.commands.controller;

import ru.bardinpetr.itmo.lab5.client.parser.CommandParser;
import ru.bardinpetr.itmo.lab5.client.parser.CommandRegister;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.tui.cli.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.tui.commands.controller.exceptions.ScriptExecuteException;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for script executing. It will be validated during executing.
 */
public class CommandScriptController {

    public CommandScriptController() {
    }

    /**
     * method for starting script executing
     *
     * @param inputStream Input Stream for scanner.
     * @return list of fulfilled commands
     */
    public List<APICommand> run(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);

        CommandParser cmdParser = (new CommandRegister()).getParser(scanner, new ConsolePrinter(), () -> {
            throw new RuntimeException("invalid script");
        });

        List<APICommand> commandList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            try {
                APICommand cmd = cmdParser.parse();
                commandList.add(cmd);
            } catch (ParserException e) {
                throw new ScriptExecuteException("Invalid script: " + e.getMessage());
            }
        }
        return commandList;
    }
}