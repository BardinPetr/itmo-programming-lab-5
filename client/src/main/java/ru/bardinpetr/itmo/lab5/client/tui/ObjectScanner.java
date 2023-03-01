package ru.bardinpetr.itmo.lab5.client.tui;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.parser.DescriptionHolder;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.models.commands.validation.IValidator;
import ru.bardinpetr.itmo.lab5.models.fields.FieldWithDesc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Class for interacting data objects
 */
public class ObjectScanner {
    private Scanner scanner;
    private View viewer;
    private ObjectMapper mapper;

    public ObjectScanner(Scanner scanner, View viewer, ObjectMapper mapper) {
        this.scanner = scanner;
        this.viewer = viewer;
        this.mapper = mapper;
    }


    private String scan() {
        String string = scanner.nextLine();
        return string == null ? "" : string;
    }

    /**
     * Method for single value interaction
     *
     * @param kClass - Class of value
     * @param <T>    Type of value
     * @return object if required type
     */
    private <T> T interactValue(Class<T> kClass) throws IllegalArgumentException, ParserException {
        if (!DescriptionHolder.dataDescriptions.containsKey(kClass))
            try {
                return mapper.convertValue(scan(), kClass);
            } catch (IllegalArgumentException e) {
                throw new ParserException("Invalid argument");
            }
        else {
            return scan(kClass);
        }

    }

    /**
     * Method for integrating thw whole object
     *
     * @param kClass Object class
     * @param <T>    Object class
     * @return Object
     */
    public <T> T scan(Class<T> kClass) throws ParserException {
        Map<String, Object> objectMap = new HashMap<>();
        List<FieldWithDesc<?>> fields = DescriptionHolder.dataDescriptions.get(kClass);
        for (var i : fields) {
            viewer.show(i.getPromptMsg());
            var value = interactValue(i.getValueClass());

            IValidator val = i.getValidator();
            @SuppressWarnings("unchecked")
            var res = val.validate(i.getValueClass().cast(value));
            if (res.isAllowed()) {
                objectMap.put(i.getName(), value);
            } else {
                throw new ParserException("Invalid argument: " + res.getMsg());
            }
        }
        return mapper.convertValue(objectMap, kClass);
    }
}
