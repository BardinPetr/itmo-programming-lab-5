package ru.bardinpetr.itmo.lab5.client.tui.commands.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.parser.CommandParser;
import ru.bardinpetr.itmo.lab5.client.parser.CommandRegister;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.tui.View;
import ru.bardinpetr.itmo.lab5.client.tui.commands.controller.exceptions.ScriptExecuteException;
import ru.bardinpetr.itmo.lab5.common.serdes.ObjectMapperFactory;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandScriptController {
    private final ObjectMapper mapper = ObjectMapperFactory.createMapper();
    private final CommandRegister cmdRegister = new CommandRegister();

    public CommandScriptController() {
    }

    public List<Command> run(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);

        CommandParser cmdParser = cmdRegister.getParser(scanner, new View() {
        });

        List<Command> commandList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            try {
                Command cmd = cmdParser.parse();
                commandList.add(cmd);
            } catch (ParserException e) {
                throw new ScriptExecuteException("Invalid script: " + e.getMessage());
            }
        }
        return commandList;
    }
}