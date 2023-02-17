package ru.bardinpetr.itmo.lab5.client.tui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import ru.bardinpetr.itmo.lab5.client.parser.DescriptionHolder;
import ru.bardinpetr.itmo.lab5.models.fields.FieldWithDesc;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Class for interacting data objects
 */
public class ObjectScanner {
    private static final View viewer = new ConsolePrinter();
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
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
        //mapper.registerModule(new ParameterNamesModule());
    }

    /**
     * Method for single value interaction
     *
     * @param kClass - Class of value
     * @param <T>    Type of value
     * @return object if required type
     */
    private static <T> T interactValue(Class<T> kClass) {
        Scanner scanner = new Scanner(System.in);

        try {
            if (!DescriptionHolder.dataDescriptions.containsKey(kClass))
                return mapper.convertValue(scanner.next(), kClass);
            else {
                return scan(kClass);
            }
        } catch (IllegalArgumentException e) {
            //viewer.show(e.getLocalizedMessage());
            viewer.show("Some went wrong. Please, try again.");
            return interactValue(kClass);
        }
    }

    /**
     * Method for integrating thw whole object
     *
     * @param kClass Object class
     * @param <T>    Object class
     * @return Object
     */
    public static <T> T scan(Class<T> kClass) {
        Map<String, Object> objectMap = new HashMap<>();
        List<FieldWithDesc> fields = DescriptionHolder.dataDescriptions.get(kClass);
        for (var i : fields) {
            while (true) {
                viewer.show(i.getPromptMsg());

                var value = interactValue(i.getValueClass());
                if (i.getValidator().validate(value).allowed) {
                    objectMap.put(i.getName(), value);
                    break;
                } else {
                    @SuppressWarnings("unchecked")
                    var res = i.getValidator().validate(value);
                    viewer.show(res.msg);
                }
            }
        }

        return mapper.convertValue(objectMap, kClass);
    }
}
