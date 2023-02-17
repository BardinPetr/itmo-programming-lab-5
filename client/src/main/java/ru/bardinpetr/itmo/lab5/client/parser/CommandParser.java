package ru.bardinpetr.itmo.lab5.client.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.tui.ObjectScanner;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.validation.ValueDeserializer;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;


public class CommandParser {
    private final HashMap<String, Command> cmdMap;
    ObjectMapper mapper = new ObjectMapper();


    public CommandParser(HashMap<String, Command> cmdMap) {
        this.cmdMap = cmdMap;
        String timeFormat = "dd-MM-yyyy";
        mapper.setDateFormat(new SimpleDateFormat(timeFormat));
        var formatter = new DateTimeFormatterBuilder().appendPattern(timeFormat)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter(); //TODO reformat
        var timeModule =
                new JavaTimeModule()
                        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter))
                        .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));

        mapper.registerModule(timeModule);
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
