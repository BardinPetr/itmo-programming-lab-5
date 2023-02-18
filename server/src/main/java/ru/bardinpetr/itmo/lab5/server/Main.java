package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.models.commands.AddCommand;
import ru.bardinpetr.itmo.lab5.models.data.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws FileAccessException {
        var w1 = new Worker(
                23L, LocalDateTime.now(), "",
                new Coordinates(234, 235), 213F,
                Date.from(Instant.now()),
                new Organization("asd", OrganizationType.COMMERCIAL),
                LocalDateTime.now(),
                Position.CLEANER
        );

        var executor = new MainExecutor(new FileIOController("db.xml"));

        var res = executor.execute(
                new AddCommand(w1)
        );

        System.out.println(res.getText());
        System.out.println(res.getPayload().getUserMessage());
    }
}
