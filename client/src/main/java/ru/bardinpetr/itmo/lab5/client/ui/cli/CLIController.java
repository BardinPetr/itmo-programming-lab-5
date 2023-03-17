package ru.bardinpetr.itmo.lab5.client.ui.cli;

import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ObjectScanner;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ParserException;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;

import java.io.InputStream;
import java.util.Scanner;

public class CLIController implements UIReceiver {
    private final ConsolePrinter printer;
    private final ObjectScanner objectScanner;
    private final Scanner scanner;

    public CLIController(ConsolePrinter printer, InputStream inputStream) {
        this.objectScanner = new ObjectScanner(printer, new Scanner(inputStream));
        this.printer = printer;
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public <T> T fill(Class<T> target, T defaultObject) {
        try {
            return objectScanner.scan(target, defaultObject);
        } catch (ParserException e) {
            throw new RuntimeException("Parse exception: " + e.getMessage());
        }
    }

    @Override
    public boolean hasNextLine() {
        try {
            return scanner.hasNextLine();
        } catch (IllegalStateException ignored) {
            return false;
        }
    }

    @Override
    public String nextLine() {
        try {
            return scanner.nextLine();
        } catch (Exception ignored) {
            return null;
        }
    }

    @Override
    public void display(String text) {
        printer.display(text);
    }

    @Override
    public void interactSuggestion() {
        System.out.print("> ");
    }

    @Override
    public void ok() {
        printer.display("Command successful");
    }

    @Override
    public void error(String message) {
        printer.display("Error: %s".formatted(message));
    }
}
