package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.models.data.*;
import ru.bardinpetr.itmo.lab5.models.serdes.XMLSerDesService;
import ru.bardinpetr.itmo.lab5.models.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.server.filedb.FileIOController;
import ru.bardinpetr.itmo.lab5.server.filedb.exceptions.FileAccessException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SerDesException, FileAccessException {
        var a = new XMLSerDesService<WorkerCollection>();
        var b = new WorkerCollection();
        b.add(
                Worker.builder()
                        .coordinates(Coordinates.builder().x(1).y(123).build())
                        .endDate(LocalDateTime.now())
                        .name("1")
                        .organization(
                                Organization.builder().fullName("123").type(OrganizationType.COMMERCIAL).build())
                        .position(Position.CLEANER)
                        .salary(13f)
                        .startDate(Date.from(Instant.now()))
                        .build()
        );
        b.add(
                Worker.builder()
                        .coordinates(Coordinates.builder().x(1).y(123).build())
                        .endDate(LocalDateTime.now())
                        .name("1")
                        .organization(
                                Organization.builder().fullName("123").type(OrganizationType.COMMERCIAL).build())
                        .position(Position.CLEANER)
                        .salary(13f)
                        .startDate(Date.from(Instant.now()))
                        .build()
        );
//        var s = a.serialize(b);
//        System.out.println(s);
//        System.out.println(a.deserialize(s, WorkerCollection.class));

        var f = new FileIOController("/home/petr/Desktop/itmo-programming-lab-5/f.dat");
        f.write(a.serialize(b));
        System.out.println(Arrays.toString(f.read()));
        f.clear();
        System.out.println(Arrays.toString(f.read()));
    }
}
