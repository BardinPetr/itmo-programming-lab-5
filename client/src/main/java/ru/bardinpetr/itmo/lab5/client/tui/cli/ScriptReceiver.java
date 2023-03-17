package ru.bardinpetr.itmo.lab5.client.tui.cli;

import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.tui.exception.NoSuchDataException;

public interface ScriptReceiver {
    default <T> T fill(Class<T> target) throws NoSuchDataException, ParserException {
        return fill(target, null);
    }

    <T> T fill(Class<T> target, T defaultObject) throws NoSuchDataException, ParserException;
}
