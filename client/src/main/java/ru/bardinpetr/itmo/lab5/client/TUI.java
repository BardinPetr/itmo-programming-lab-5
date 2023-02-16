package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.parser.CommandParser;
import ru.bardinpetr.itmo.lab5.client.parser.CommandRegister;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;

import java.util.Map;
import java.util.Scanner;

public class TUI {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        View view = new ConsolePrinter();
        Map<TextKeys, String> texts = RussianText.getMap();
        CommandRegister cmdRegister = new CommandRegister();

        try {
            CommandParser parser = cmdRegister.regist();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        view.show(texts.get(TextKeys.GREEETING));

        String commandString = scanner.next();



    }
}
