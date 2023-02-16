package ru.bardinpetr.itmo.lab5.client.tui;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.View;
import ru.bardinpetr.itmo.lab5.models.commands.FiledWithDesc;
import ru.bardinpetr.itmo.lab5.client.DescriptionHolder;

import java.text.SimpleDateFormat;
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

    static{
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
    }
    /**
     *  Method for single value interaction
     * @param kClass - Class of value
     * @return object if required type
     * @param <T> Type of value
     */
    private static <T> T interactValue(Class<T> kClass){
        Scanner scanner = new Scanner(System.in);

        try {
            if (!DescriptionHolder.dataDescriptions.containsKey(kClass))
                return mapper.convertValue(scanner.next(), kClass);
            else {
                return scan(kClass);
            }
        }
        catch (IllegalArgumentException e){
            //viewer.show(e.getLocalizedMessage());
            viewer.show("Some went wrong. Please, try again.");
            return interactValue(kClass);
        }
    }

    /**
     * Method for integrating thw whole object
     * @param kClass Object class
     * @return Object
     * @param <T> Object class
     */
    public static <T> T scan(Class<T> kClass){
        Map<String, Object> objectMap = new HashMap<>();
        List<FiledWithDesc> fields = DescriptionHolder.dataDescriptions.get(kClass);
        for (var i: fields){
            while (true) {
                viewer.show(i.getPromtMsg());

                var value = interactValue(i.getValueClass());
                if (i.getValidator().validate(value).allowed) {
                    objectMap.put(i.getName(), value);
                    break;
                }
                else {
                    viewer.show(i.getValidator().validate(value).msg);
                }
            }
        }
        return mapper.convertValue(objectMap, kClass);
    }
}
