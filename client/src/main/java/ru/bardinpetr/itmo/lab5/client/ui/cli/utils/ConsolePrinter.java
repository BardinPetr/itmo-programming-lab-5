package ru.bardinpetr.itmo.lab5.client.ui.cli.utils;

public class ConsolePrinter {
    public void display(String text) {
        System.out.println(text);
    }

    public void displayInLine(String text) {
        System.out.print(text);
    }

    public static ConsolePrinter getStub() {
        return new ConsolePrinter() {
            @Override
            public void display(String text) {
            }

            @Override
            public void displayInLine(String text) {
            }
        };
    }
}
