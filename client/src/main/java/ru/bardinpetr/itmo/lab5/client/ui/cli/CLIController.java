package ru.bardinpetr.itmo.lab5.client.ui.cli;

import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ObjectScanner;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ParserException;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;

import java.util.Scanner;

public class CLIController implements UIReceiver {
    private final ConsolePrinter printer;
    private final ObjectScanner objectScanner;
    private final Scanner scanner;

    public CLIController(ConsolePrinter printer, Scanner scanner) {
        this.objectScanner = new ObjectScanner(printer, scanner);
        this.printer = printer;
        this.scanner = scanner;
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
        return scanner.hasNextLine();
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public void display(String text) {
        printer.display(text);
    }

    @Override
    public void ok() {
        printer.display("OK!");
    }

    @Override
    public void error(String message) {
        printer.display("Error %s".formatted(message));
    }
}
