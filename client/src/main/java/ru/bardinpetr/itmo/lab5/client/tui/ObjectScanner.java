package ru.bardinpetr.itmo.lab5.client.tui;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.parser.DescriptionHolder;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.models.commands.validation.IValidator;
import ru.bardinpetr.itmo.lab5.models.fields.FieldWithDesc;

import java.util.*;

/**
 * Class for interacting data objects
 */
public class ObjectScanner {
    private final Map<Class<?>, List<FieldWithDesc<?>>> dataDescription = DescriptionHolder.dataDescriptions;
    private final Scanner scanner;
    private final View viewer;
    private final ObjectMapper mapper;
    Runnable callback;

    public ObjectScanner(Scanner scanner, View viewer, ObjectMapper mapper, Runnable callback) {
        this.scanner = scanner;
        this.viewer = viewer;
        this.mapper = mapper;
        this.callback = callback;
    }


    private String scan() {
        String string = "";
        try {
            string = scanner.nextLine();
        } catch (NoSuchElementException ignored) {
            callback.run();
        }
        return string.isEmpty() ? null : string;
    }

    /**
     * Method for single value interaction
     *
     * @param kClass - Class of value
     * @param <T>    Type of value
     * @return object if required type
     */
    private <T> T interactValue(Class<T> kClass) throws ParserException, RuntimeException {
        try {
            if (!dataDescription.containsKey(kClass))
                return mapper.convertValue(scan(), kClass);
            else
                return scan(kClass);
        } catch (IllegalArgumentException e) {
            throw new ParserException("Invalid argument");
        }
    }

    /**
     * Method for integrating thw whole object
     *
     * @param kClass class of scanned object
     * @param <T>    class of scanned object
     * @return fulfilled object
     */
    public <T> T scan(Class<T> kClass) throws ParserException {
        Map<String, Object> objectMap = new HashMap<>();
        List<FieldWithDesc<?>> fields = dataDescription.get(kClass);

        for (int i = 0; i < fields.size(); ) {
            var cur = fields.get(i);
            viewer.show(cur.getPromptMsg());

            if (dataDescription.containsKey(cur.getValueClass()) && cur.isNullAble()) {
                viewer.show("If object does not exist enter N. To continue interaction enter C");
                String answer = scanner.nextLine();
                if (answer.equals("N")) {
                    objectMap.put(cur.getName(), null);
                    continue;
                }
                if (!answer.equals("C")) {
                    viewer.show("Invalid argument");
                    continue;
                }
                viewer.show("Continue interaction");
            }
            Object value;
            try {
                value = interactValue(cur.getValueClass());
            } catch (ParserException ex) {
                viewer.show("Invalid argument");
                continue;
            } catch (RuntimeException ex) {
                throw new ParserException(ex.getMessage());
            }
            if ((!cur.isNullAble()) && value == null) {
                viewer.show("Invalid argument: Argument can't be null");
                continue;
            }

            IValidator val = cur.getValidator();
            @SuppressWarnings("unchecked")
            var res = val.validate(cur.getValueClass().cast(value));
            if (res.isAllowed()) {
                objectMap.put(cur.getName(), value);
            } else {
                viewer.show("Invalid argument: " + res.getMsg());
            }

            i++;
        }
        return mapper.convertValue(objectMap, kClass);
    }
}
