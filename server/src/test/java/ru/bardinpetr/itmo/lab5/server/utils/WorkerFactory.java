package ru.bardinpetr.itmo.lab5.server.utils;

import ru.bardinpetr.itmo.lab5.models.data.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

public class WorkerFactory {

    private static int id = 0;
    private static int x = 10;
    private static int y = 10;
    private static int salary = 10;

    public static Worker create() {
//        var zoned = ZonedDateTime.of(2004, 4, 27, 1, 2, 3, 1000, ZoneId.systemDefault());
//        var local = LocalDateTime.now();
//        var date = new Date();
        var coords = new Coordinates(x++, y++);
        var org = new Organization("test_org%d".formatted(id), OrganizationType.COMMERCIAL);
        return new Worker(
                id++,
                ZonedDateTime.now(),
                "test_name%d".formatted(id),
                coords,
                salary++,
                new Date(),
                org,
                LocalDate.now(),
                Position.CLEANER
        );
    }
}
