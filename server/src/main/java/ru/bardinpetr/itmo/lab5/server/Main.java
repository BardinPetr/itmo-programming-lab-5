package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.OrganizationType;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws FileAccessException {
        var w1 = new Worker(
                23, ZonedDateTime.now(), "nj",
                new Coordinates(234, 235), 213F,
                Date.from(Instant.now()),
                new Organization("asd", OrganizationType.COMMERCIAL),
                LocalDate.now(),
                null
        );
    }
}
