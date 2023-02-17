package ru.bardinpetr.itmo.lab5.client.texts;


import java.util.HashMap;
import java.util.Map;

public class RussianText {

    private final static Map<TextKeys, String> textList = new HashMap<>(){{
        put(TextKeys.GREEETING, "Hi! It's Bardin Pert and Zaytsev Artem lab5 program. Enter \"help\" to see more information");
        put(TextKeys.HELP, """
            Ниже представлен список команд в следующем виде
            <название команды> <аргументы>: описание команды
            Аргументы вводятся в строке с комнатой. Если аргумент указан в {}, то его ввод происходит поэтапно.
                        
               - help : вывести справку по доступным командам
               - info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
               - show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
               - add {element} : добавить новый элемент в коллекцию
               - update id {element} : обновить значение элемента коллекции, id которого равен заданному
               - remove_by_id id : удалить элемент из коллекции по его id
               - clear : очистить коллекцию
               - save : сохранить коллекцию в файл
               - execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
               - exit : завершить программу (без сохранения в файл)
               - add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
               - add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
               - remove_greater {element} : удалить из коллекции все элементы, превышающие заданный
               - filter_less_than_position position : вывести элементы, значение поля position которых меньше заданного
            		варианты поля position:
            				CLEANER
            				MANAGER_OF_CLEANING
            				ENGINEER
            				LEAD_DEVELOPER
            				HEAD_OF_DEPARTMENT
            	
               - print_descending : вывести элементы коллекции в порядке убывания
               - print_unique_organization : вывести уникальные значения поля organization всех элементов в коллекции"""
        );

        put(TextKeys.WORKERINTERACT, "Enter employee details");
        put(TextKeys.NAMEINTERACT, "Enter a name");
        put(TextKeys.COORDINATESINTERACT, "Enter coordinates");
        put(TextKeys.ORGANIZATIONINTERACT, "Enter an organization");
        put(TextKeys.SALARYINTERACT, "Enter salary");
        put(TextKeys.COORXINTERACT, "Enter x coordinate");
        put(TextKeys.COORYINTERACT, "Enter y coordinate");
        put(TextKeys.STARTDAYINTERACT, "Enter start date");
        put(TextKeys.ENDNDATEINTERACT, "Enter end date");
        put(TextKeys.POSITIONINTERACT, "Enter position from list:" +
                "    ENGINEER,\n" +
                "    HEAD_OF_DEPARTMENT,\n" +
                "    LEAD_DEVELOPER,\n" +
                "    CLEANER,\n" +
                "    MANAGER_OF_CLEANING");
        put(TextKeys.ORGANISATIONNAMEINTERACT, "Enter organisation name");
        put(TextKeys.ORGANISATIONTYPEINTERACT, "Enter organisation type from list:" +
                "    COMMERCIAL,\n" +
                "    PUBLIC,\n" +
                "    PRIVATE_LIMITED_COMPANY,\n" +
                "    OPEN_JOINT_STOCK_COMPANY");


    }};


    public static Map<TextKeys, String> getMap() {
        return textList;
    }
}
