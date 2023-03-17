package ru.bardinpetr.itmo.lab5.client.tui.cli;

public class ConsolePrinter {
    public void display(String text) {
        System.out.println(text);
    }

    public void displayInLine(String text) {
        System.out.print(text);
    }

    public void suggestInput() {
        System.out.print("> ");
    }

    public static ConsolePrinter getStub() {
        return new ConsolePrinter() {
            @Override
            public void display(String text) {
            }

            @Override
            public void displayInLine(String text) {
            }

            @Override
            public void suggestInput() {
            }
        };
    }
}
