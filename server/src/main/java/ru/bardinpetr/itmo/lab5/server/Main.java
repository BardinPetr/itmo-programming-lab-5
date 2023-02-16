package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.models.commands.ShowCommand;
import ru.bardinpetr.itmo.lab5.models.data.*;
import ru.bardinpetr.itmo.lab5.models.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.server.dao.WorkersDAO;
import ru.bardinpetr.itmo.lab5.server.executor.Executor;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;
import ru.bardinpetr.itmo.lab5.server.filedb.storage.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.server.filedb.storage.exceptions.InvalidDataFileException;
import ru.bardinpetr.itmo.lab5.server.filedb.storage.io.FileIOController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SerDesException, FileAccessException, InvalidDataFileException {
        var w = new WorkerCollection();
        Worker.builder().endDate(LocalDateTime.now());
        var w1 = Worker.builder()
                .coordinates(Coordinates.builder().x(1).y(123).build())
                .endDate(LocalDateTime.now())
                .name("1")
                .organization(
                        Organization.builder().fullName("123").type(OrganizationType.COMMERCIAL).build())
                .position(Position.CLEANER)
                .salary(13f)
                .startDate(Date.from(Instant.now()))
                .build();
        var w2 = Worker.builder()
                .coordinates(Coordinates.builder().x(1).y(123).build())
                .endDate(LocalDateTime.now())
                .name("1")
                .organization(
                        Organization.builder().fullName("123").type(OrganizationType.COMMERCIAL).build())
                .position(Position.CLEANER)
                .salary(13f)
                .startDate(Date.from(Instant.now()))
                .build();

        w.add(w1);

        var file = new FileIOController("/home/petr/Desktop/itmo-programming-lab-5/db.xml");
        var db = new FileDBController<>(file, WorkerCollection.class);

        var dao = new WorkersDAO(db);

        var ex = new Executor();

        ex.registerOperation(ShowCommand.class, (ShowCommand cmd) -> {
            ShowCommand.ShowCommandResponse res = cmd.createResponse();
            res.test = "ok";
            return res;
        });

        System.out.println(ex.execute(new ShowCommand()));
    }
}
