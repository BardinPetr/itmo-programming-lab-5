package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;

import java.util.Map;
import java.util.Scanner;

public class TUI {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Shower shower = new ConsolePrinter();
        Map<TextKeys, String> texts = (new RussianText()).getMap();

        shower.show(texts.get(TextKeys.GREEETING));

        String commandString = scanner.next();



    }
}
