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
    private <T> T interactValue(Class<T> kClass) throws IllegalArgumentException, ParserException, RuntimeException {
        if (!dataDescription.containsKey(kClass))
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
     * @param kClass class of scanned object
     * @param <T>    class of scanned object
     * @return fulfilled object
     */
    public <T> T scan(Class<T> kClass) throws ParserException {
        Map<String, Object> objectMap = new HashMap<>();
        List<FieldWithDesc<?>> fields = dataDescription.get(kClass);

        for (int j = 0; j < fields.size(); j++) {
            var i = fields.get(j);
            viewer.show(i.getPromptMsg());

            if (dataDescription.containsKey(i.getValueClass()) && i.isNullAble()) {
                viewer.show("If object does not exist enter N. To continue interaction enter C");
                String answer = scanner.nextLine();
                if (answer.equals("N")) {
                    objectMap.put(i.getName(), null);
                    continue;
                }
                if (!answer.equals("C")) {
                    viewer.show("Invalid argument");
                    j--;
                    continue;
                }
                viewer.show("Continue interaction");
            }
            Object value;
            try {
                value = interactValue(i.getValueClass());
            } catch (ParserException ex) {
                viewer.show("Invalid argument");
                j--;
                continue;
            } catch (RuntimeException ex) {
                throw new ParserException(ex.getMessage());
            }
            if ((!i.isNullAble()) && value == null) {
                viewer.show("Invalid argument: Argument can't be null");
                j--;
                continue;
            }

            IValidator val = i.getValidator();
            @SuppressWarnings("unchecked")
            var res = val.validate(i.getValueClass().cast(value));
            if (res.isAllowed()) {
                objectMap.put(i.getName(), value);
            } else {
                viewer.show("Invalid argument: " + res.getMsg());
                j--;
            }
        }
        return mapper.convertValue(objectMap, kClass);
    }
}
