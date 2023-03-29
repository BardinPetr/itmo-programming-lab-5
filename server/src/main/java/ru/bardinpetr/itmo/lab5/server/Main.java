package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.models.data.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws FileAccessException {
        new Worker(
                0,
                ZonedDateTime.now(),
                "Artem",
                12.3f,
                new Date(2023 - 1900, Calendar.MARCH, 2),
                LocalDate.of(2023, Month.MARCH, 2),
                new Coordinates(13, 12),
                new Organization("ITMO", OrganizationType.PUBLIC),
                Position.CLEANER
        );


    }
}
