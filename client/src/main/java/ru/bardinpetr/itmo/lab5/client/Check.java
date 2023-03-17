package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ObjectScanner;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ParserException;
import ru.bardinpetr.itmo.lab5.models.data.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Check {
    public static void main(String[] args) throws ParserException {
        var t = new Worker(
                0,
                ZonedDateTime.now(),
                "Artem",
                new Coordinates(13, 12),
                12.3f,
                new Date(2023 - 1900, Calendar.MARCH, 2),
                new Organization("ITMO", OrganizationType.PUBLIC),
                LocalDate.of(2023, Month.MARCH, 2),
                Position.CLEANER
        );

        var scanner = new ObjectScanner(new ConsolePrinter(), new Scanner(System.in));
        System.out.println(scanner.scan(Worker.class, t));
    }
}
