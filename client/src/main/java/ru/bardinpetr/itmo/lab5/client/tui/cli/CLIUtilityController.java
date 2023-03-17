package ru.bardinpetr.itmo.lab5.client.tui.cli;

import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;

public class CLIUtilityController implements UIReceiver {
    private final ConsolePrinter printer;
    private final ObjectScanner objectScanner = new ObjectScanner();

    public CLIUtilityController() {
        this.printer = new ConsolePrinter();
    }

    @Override
    public <T> T fill(Class<T> target, T defaultObject) {
        try {
            return objectScanner.scan(target, defaultObject);
        } catch (ParserException e) {
            throw new RuntimeException(e);
        }
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
