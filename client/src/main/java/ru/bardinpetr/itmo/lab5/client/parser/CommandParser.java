package ru.bardinpetr.itmo.lab5.client.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.tui.newThings.ObjectScanner;
import ru.bardinpetr.itmo.lab5.client.tui.Printer;
import ru.bardinpetr.itmo.lab5.common.serdes.ValueDeserializer;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for parsing command from String. Text is entered via scanner.
 */
public class CommandParser {
    private final HashMap<String, Command> cmdMap;
    private ObjectMapper mapper;
    private Scanner scanner;
    private Printer viewer;
    private ObjectScanner objectScanner;

    public CommandParser(HashMap<String, APICommand> cmdMap, ObjectMapper mapper, Scanner scanner, Printer viewer, Runnable callback) {
        this.cmdMap = cmdMap;
        this.mapper = mapper;
        this.viewer = viewer;
        this.scanner = scanner;
        this.objectScanner = new ObjectScanner();

    }

    /**
     * Methid for parsing command's arguments from string. It uses command's inline
     * and composite arguments.
     *
     * @return Command with all completed fields
     * @throws ParserException Exception can be thrown during parsing type of validation
     */
    public APICommand parse() throws ParserException {
        ValueDeserializer valueDes = new ValueDeserializer();

        String[] userArgs = scanner.nextLine().split(" ");
        String commandName = userArgs[0];

        if (!cmdMap.containsKey(commandName)) throw new ParserException("invalid command name \"" + commandName + "\"");
        if (userArgs.length - 1 != cmdMap.get(commandName).getInlineArgs().length)
            throw new ParserException("arguments amount exception");

        var inlineArgs = cmdMap.get(commandName).getInlineArgs();
        Map<String, Object> objectMap = new HashMap<>();

        for (int i = 0; i < userArgs.length - 1; i++) {
            var value = valueDes.deserialize(inlineArgs[i].getValueClass(), userArgs[i + 1]);
            objectMap.put(inlineArgs[i].getName(), value);
        }
        var interactArgs = cmdMap.get(commandName).getInteractArgs();
        for (Field i : interactArgs) {
            objectMap.put(i.getName(), objectScanner.scan(i.getValueClass(), null));
        }

        return mapper.convertValue(objectMap, cmdMap.get(commandName).getClass());
    }
}
