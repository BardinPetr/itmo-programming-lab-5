package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.models.commands.api.ShowCommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.Response;
import ru.bardinpetr.itmo.lab5.models.data.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var w = new Worker(
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

        var cmdres = new ShowCommand().createResponse();
        cmdres.setResult(List.of(w));
        var in = Response.success(cmdres);

        var sd = new JSONSerDesService<>(Response.class);
        try {
            var res = sd.serialize(in);
            System.out.println(new String(res));
            var des = sd.deserialize(res);
            System.out.println(des.getClass());
            System.out.println(des);
            var response = (ShowCommand.ShowCommandResponse) des.getPayload();
            System.out.println(response.getUserMessage());
        } catch (SerDesException e) {
            throw new RuntimeException(e);
        }
    }
}
