package ru.bardinpetr.itmo.lab5.client;

import java.util.Scanner;

public class TUI {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Shower shower = new ConsolePrinter();

        shower.show(Texts.getGreetingText());

        String commandString = scanner.next();
        String command = commandString.split(" ")[0];

        if (command.equals("help")) shower.show(Texts.helpText);
    }
}
