package ru.bardinpetr.itmo.lab5.client.ui.cli;

import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ObjectScanner;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.NotRepeatableException;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ParserException;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;

import java.io.InputStream;
import java.util.Scanner;

public class CLIController implements UIReceiver {
    private final ConsolePrinter printer;
    private final ObjectScanner objectScanner;
    private final Scanner scanner;
    private final boolean isRepeatable;


    public CLIController(ConsolePrinter printer, InputStream inputStream, boolean isRepeatable) {
        this.scanner = new Scanner(inputStream);
        this.objectScanner = new ObjectScanner(printer, scanner);
        this.printer = printer;
        this.isRepeatable = isRepeatable;
    }

    @Override
    public <T> T fill(Class<T> target, T defaultObject) {
        try {
            var resp = objectScanner.scan(target, defaultObject);
            if ((!isRepeatable) & resp.countOfRepeat > 0) throw new NotRepeatableException();
            return resp.getObject();
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
    public void ok(String cmd) {
        printer.display("Command %s successful".formatted(cmd));
    }

    @Override
    public void error(String message) {
        printer.display("Error: %s".formatted(message));
    }
}
