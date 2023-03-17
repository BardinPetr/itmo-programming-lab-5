package ru.bardinpetr.itmo.lab5.client.ui.cli.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.api.commands.DescriptionHolder;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ParserException;
import ru.bardinpetr.itmo.lab5.common.serdes.ObjectMapperFactory;
import ru.bardinpetr.itmo.lab5.models.commands.validation.IValidator;
import ru.bardinpetr.itmo.lab5.models.fields.FieldWithDesc;

import java.util.*;

/**
 * Class for interacting data objects
 */
public class ObjectScanner {
    private final Map<Class<?>, List<FieldWithDesc<?>>> dataDescription = DescriptionHolder.dataDescriptions;
    private final Scanner scaner;
    private final ConsolePrinter printer;
    private final ObjectMapper mapper = ObjectMapperFactory.createMapper();
    Runnable callback;

    public ObjectScanner(ConsolePrinter printer, Scanner scaner) {
        this.printer = printer;
        this.scaner = scaner;
    }

    private String scan() {
        String string = "";
        string = scaner.nextLine();

        return string.isEmpty() ? null : string;
    }

    /**
     * Method for single value interaction
     *
     * @param kClass - Class of value
     * @param <T>    Type of value
     * @return object if required type
     */
    private <T> T interactValue(Class<T> kClass, T defaultValue) throws ParserException, RuntimeException {
        try {
            if (!dataDescription.containsKey(kClass))
                return mapper.convertValue(scan(), kClass);
            else
                return scan(kClass, defaultValue);
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
    public <T> T scan(Class<T> kClass, T defaultObject) throws ParserException, NoSuchElementException {
        var defaultObjectMap = mapper.convertValue(defaultObject, HashMap.class);

        Map<String, Object> objectMap = new HashMap<>();
        List<FieldWithDesc<?>> fields = dataDescription.get(kClass);
        if (fields == null) throw new ParserException(kClass.getName());

        for (int i = 0; i < fields.size(); i++) {
            var cur = fields.get(i);

            Object curDefaultVar;
            printer.display(cur.getPromptMsg());
            if (defaultObjectMap != null) {
                curDefaultVar = defaultObjectMap.get(cur.getName());
                String str;
                if (curDefaultVar != null)
                    str = curDefaultVar.toString();
                else
                    str = "null";

                printer.displayInLine(String.format("Default is \"%s\". ", str));
                printer.display("Press N to enter a new value, or press Enter to continue with default one.");
                String resp = scaner.nextLine();
                if (resp.equals("")) {
                    objectMap.put(cur.getName(), curDefaultVar);
                    printer.display("Default value was used");
                    continue;
                }
                if (!resp.equals("N")) {
                    printer.display("Invalid interact");
                    i--;
                    continue;
                }
                printer.display(cur.getPromptMsg());
            } else {
                curDefaultVar = null;
            }

            while (enterField(cur, objectMap, curDefaultVar) == 1) {
            }
        }
        return mapper.convertValue(objectMap, kClass);
    }

    /**
     * @param cur           current field
     * @param objectMap     Map of building object
     * @param curDefaultVar default value of current value
     * @param <T>           type of field
     * @return completed value field
     * @throws ParserException
     */
    private <T> int enterField(FieldWithDesc<T> cur, Map<String, Object> objectMap, Object curDefaultVar) throws ParserException {
        if (cur.isNullAble()) {
            printer.display("If object does not exist press Enter. To continue interaction enter C");
            String answer = scaner.nextLine();
            if (answer.equals("")) {
                objectMap.put(cur.getName(), null);
                return 0;
            }
            if (!answer.equals("C")) {
                printer.display("Invalid argument");
                printer.display(cur.getPromptMsg());
                return 1;
            }
            printer.display("Continue interaction");
            printer.display(cur.getPromptMsg());
        }


        Object value = null;
        try {
            var cls = cur.getValueClass();
            value = interactValue(cls, mapper.convertValue(curDefaultVar, cls));
        } catch (ParserException ex) {
            printer.display("Invalid argument");
            return 1;
        } catch (RuntimeException ex) {
            throw new ParserException(ex.getMessage());
        }
        if ((!cur.isNullAble()) && value == null) {
            printer.display("Invalid argument: Argument can't be null");

            return 1;
        }

        IValidator val = cur.getValidator();
        @SuppressWarnings("unchecked")
        var res = val.validate(cur.getValueClass().cast(value));
        if (res.isAllowed()) {
            objectMap.put(cur.getName(), value);
        } else {
            printer.display("Invalid argument: " + res.getMsg());
            return 1;
        }
        return 0;
    }
}
