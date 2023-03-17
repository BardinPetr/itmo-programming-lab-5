package ru.bardinpetr.itmo.lab5.client;

import org.junit.jupiter.api.Test;
import ru.bardinpetr.itmo.lab5.client.parser.APICommandRegistry;
import ru.bardinpetr.itmo.lab5.client.parser.CommandParser;
import ru.bardinpetr.itmo.lab5.client.tui.cli.ConsolePrinter;

import java.util.Scanner;

public class CommandParserTest {

    private CommandParser getParser(String text) {
        return APICommandRegistry.getParser(new Scanner(text), ConsolePrinter.getStub(), () -> {
            System.exit(0);
        });
    }

    @Test
    void test() {

    }
}
