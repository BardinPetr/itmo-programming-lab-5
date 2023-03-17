package ru.bardinpetr.itmo.lab5.client.tui.cli;

import ru.bardinpetr.itmo.lab5.client.parser.DescriptionHolder;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.models.fields.FieldWithDesc;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CLIUtilityController implements UIReceiver {
    private final ConsolePrinter printer;
    private final ObjectScanner objectScanner;
    private final Map<Class<?>, List<FieldWithDesc<?>>> map = DescriptionHolder.dataDescriptions;

    public CLIUtilityController(ConsolePrinter printer, Scanner scanner) {
        this.objectScanner = new ObjectScanner(printer, scanner);
        this.printer = printer;
    }

    @Override
    public <T> T fill(Class<T> target, T defaultObject) throws ParserException {
        return objectScanner.scan(target, defaultObject);
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
