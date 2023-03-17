package ru.bardinpetr.itmo.lab5.client.tui.cli;

import java.util.Scanner;

public class ConsoleReader {
    Scanner scanner = new Scanner(System.in);
    public String readLine(){
        return scanner.nextLine();
    }

    public boolean hasNextLine(){
        return scanner.hasNextLine();
    }
}
