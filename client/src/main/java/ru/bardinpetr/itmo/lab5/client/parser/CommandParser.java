package ru.bardinpetr.itmo.lab5.client.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.tui.ObjectScanner;
import ru.bardinpetr.itmo.lab5.models.commands.Command;
import ru.bardinpetr.itmo.lab5.models.commands.Field;
import ru.bardinpetr.itmo.lab5.models.commands.validation.ValueDeserializer;

import java.util.HashMap;


public class CommandParser {
    private final HashMap<String, Command> cmdMap;
    ObjectMapper mapper = new ObjectMapper();


    public CommandParser(HashMap<String, Command> cmdMap){
        this.cmdMap = cmdMap;
    }

    public Command parse(String commandString) throws ParserException {
        var valueDes = new ValueDeserializer();

        String[] userArgs = commandString.split(" ");
        String commandName = userArgs[0];

        if (!cmdMap.containsKey(commandName)) throw new ParserException("invalid command name");
        if (userArgs.length - 1 != cmdMap.get(commandName).getInlineArgs().length)
            throw new ParserException("arguments amount exception");

        var inlineArgs = cmdMap.get(commandName).getInlineArgs();
        HashMap<String, Object> objectMap = new HashMap<>();
        for (int i = 0; i < userArgs.length - 1; i++) {
            var value = valueDes.Deserialize(inlineArgs[i].getValueClass(), userArgs[i + 1]);
            objectMap.put(inlineArgs[i].getName(), value);
        }

        var interactArgs = cmdMap.get(commandName).getInteractArgs();
        for (Field i : interactArgs) {
            objectMap.put(i.getName(), ObjectScanner.scan(i.getValueClass()));
        }


        return mapper.convertValue(objectMap, cmdMap.get(commandName).getClass());
    }
}
