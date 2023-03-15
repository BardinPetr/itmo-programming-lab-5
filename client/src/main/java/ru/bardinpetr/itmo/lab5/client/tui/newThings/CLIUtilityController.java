package ru.bardinpetr.itmo.lab5.client.tui.newThings;

import ru.bardinpetr.itmo.lab5.client.parser.DescriptionHolder;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.tui.exception.NoSuchDataException;
import ru.bardinpetr.itmo.lab5.models.fields.FieldWithDesc;

import java.util.List;
import java.util.Map;

public class CLIUtilityController implements UIReceiver{
    private ConsolePrinter printer;
    private ConsoleReader reader;
    private ObjectScanner objectScanner = new ObjectScanner();
    private final Map<Class<?>, List<FieldWithDesc<?>>> map = DescriptionHolder.dataDescriptions;

    public CLIUtilityController() {
        this.printer = new ConsolePrinter();
        this.reader = new ConsoleReader();
    }

    @Override
    public <T> T fill(Class<T> target, T defaultObject) throws NoSuchDataException, ParserException {
        return objectScanner.scan(target, defaultObject);
    }

    @Override
    public void display(String text) {
        printer.display(text);
    }
}
