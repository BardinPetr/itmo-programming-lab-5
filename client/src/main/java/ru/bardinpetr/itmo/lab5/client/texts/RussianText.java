package ru.bardinpetr.itmo.lab5.client.texts;


import java.util.HashMap;
import java.util.Map;

public class RussianText {

    private final static Map<TextKeys, String> textList = new HashMap<>() {{
        put(TextKeys.GREEETING, "Hi! It's Bardin Petr and Zaytsev Artem lab5 program. Enter \"help\" to see more information");
        put(TextKeys.HELP, """
                Below is a list of commands in the following form
                <command name> <arguments>: command description
                Arguments are entered on the line with the room. If the argument is specified in {}, then its input occurs in stages.
                		
                - help : display help on available commands
                - info : print information about the collection to standard output (type, initialization date, number of elements, etc.)
                - show : print to standard output all elements of the collection in string representation
                - add {element} : add a new element to the collection
                - update id {element} : update the value of the collection element whose id is equal to the given one
                - remove_by_id id : remove an element from the collection by its id
                - clear : clear the collection
                - save : save collection to file
                - execute_script file_name : read and execute script from specified file. The script contains commands in the same form in which they are entered by the user in interactive mode.
                - exit : exit the program (without saving to a file)
                - add_if_max {element} : add a new element to the collection if its value is greater than the value of the largest element in this collection
                - add_if_min {element} : add a new element to the collection if its value is less than the smallest element of this collection
                - remove_greater {element} : remove from the collection all elements greater than the given one
                - filter_less_than_position position : display elements whose position field value is less than the given one
                	position field options:
                		CLEANER
                		MANAGER_OF_CLEANING
                		ENGINEER
                		LEAD_DEVELOPER
                		HEAD_OF_DEPARTMENT
                                	
                - print_descending : print the elements of the collection in descending order
                - print_unique_organization : print the unique values of the organization field of all items in the collection
                  """
        );

        put(TextKeys.WORKERINTERACT, "Enter employee details");
        put(TextKeys.NAMEINTERACT, "Enter a name");
        put(TextKeys.COORDINATESINTERACT, "Enter coordinates");
        put(TextKeys.ORGANIZATIONINTERACT, "Enter an organization");
        put(TextKeys.SALARYINTERACT, "Enter salary");
        put(TextKeys.COORXINTERACT, "Enter x coordinate");
        put(TextKeys.COORYINTERACT, "Enter y coordinate");
        put(TextKeys.STARTDAYINTERACT, "Enter start date in DD-MM-YYYY format");
        put(TextKeys.ENDNDATEINTERACT, "Enter end date in DD-MM-YYYY format");
        put(TextKeys.POSITIONINTERACT, """
                Enter position from list:
                    ENGINEER,
                    HEAD_OF_DEPARTMENT,
                    LEAD_DEVELOPER,
                    CLEANER,
                    MANAGER_OF_CLEANING""");
        put(TextKeys.ORGANISATIONNAMEINTERACT, "Enter organisation name");
        put(TextKeys.ORGANISATIONTYPEINTERACT, """
                Enter organisation type from list:
                    COMMERCIAL,
                    PUBLIC,
                    PRIVATE_LIMITED_COMPANY,
                    OPEN_JOINT_STOCK_COMPANY""");


    }};


    public static String get(TextKeys key) {
        return textList.get(key);
    }
}
