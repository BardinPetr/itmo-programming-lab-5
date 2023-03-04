package ru.bardinpetr.itmo.lab5.client;

import org.junit.jupiter.api.Test;
import ru.bardinpetr.itmo.lab5.client.parser.CommandParser;
import ru.bardinpetr.itmo.lab5.client.parser.CommandRegister;
import ru.bardinpetr.itmo.lab5.client.tui.View;

import java.util.Scanner;

public class CommandParserTest {

    private CommandParser getParser(String text) {
        var t = new CommandRegister();
        return t.getParser(new Scanner(text), new View() {
        });
    }

    @Test
    void test() {

    }
}
