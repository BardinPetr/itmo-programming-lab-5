package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CommandParser {
    private final Map<String, LinkedList<Class>> map = new HashMap<String, LinkedList<Class>>();

    public CommandParser() {
        var listOfTypes = new LinkedList<Class>();
        listOfTypes.add(Integer.class);
        map.put("remove_by_id", listOfTypes);
    }

    public Command parse(String commandString) {
        String[] args = commandString.split(" ");
        String command = args[0];

        var listOfTypes = map.get(command);

        if (listOfTypes.size() == args.length - 1) {
            /*
            for (int i=1; i<args.length; i++){
                var myClass = listOfTypes.get(i-1);
                try {
                    myClass.getConstructors()[0].newInstance();
                } catch (InstantiationException|IllegalAccessException|InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }

             */
        }

        return null;
    }
}
