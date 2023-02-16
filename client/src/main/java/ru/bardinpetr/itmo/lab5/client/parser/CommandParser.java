package ru.bardinpetr.itmo.lab5.client.parser;

import ru.bardinpetr.itmo.lab5.models.commands.Command;
import ru.bardinpetr.itmo.lab5.models.commands.validation.ValueDeserializer;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;


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
        if(userArgs.length-1 != cmdMap.get(commandName).getInlineArgs().length) throw new ParserException("arguments amount exception");

        var commandArgs = cmdMap.get(commandName).getInlineArgs();
        HashMap<String, Object> objectMap = new HashMap<>();
        for(int i = 0; i< userArgs.length-1; i++){
            var value = valueDes.Deserialize(commandArgs[i].getValueClass(), userArgs[i+1]);
            objectMap.put(commandArgs[i].getName(), value);
        }

        return mapper.convertValue(objectMap, cmdMap.get(commandName).getClass());
    }
}
