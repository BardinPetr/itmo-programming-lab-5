package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.models.commands.ExecuteScriptCommand;
import ru.bardinpetr.itmo.lab5.models.data.*;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.models.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.server.dao.workers.FileDBWorkersDAO;
import ru.bardinpetr.itmo.lab5.server.executor.Executor;
import ru.bardinpetr.itmo.lab5.server.executor.WorkersDAOExecutor;
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
        var dao = new FileDBWorkersDAO(db);
        var daoExecutor = new WorkersDAOExecutor(dao);

        var ex = new Executor();
        ex.registerOperation(ExecuteScriptCommand.class, req -> {
            var resp = req.createResponse();
            resp.setResult(ex.executeBatch(req.commands));
            return resp;
        });

        ex.registerExecutor(daoExecutor);

        System.out.println(0);
    }
}
