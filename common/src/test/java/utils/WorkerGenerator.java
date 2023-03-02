package utils;

import ru.bardinpetr.itmo.lab5.models.data.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class WorkerGenerator {
    public static Worker generateWorker() {
//        var zoned = ZonedDateTime.of(2004, 4, 27, 1, 2, 3, 1000, ZoneId.systemDefault());
//        var local = LocalDateTime.now();
//        var date = new Date();
        var coords = new Coordinates(11, 22.33f);
        var org = new Organization("test_org", OrganizationType.COMMERCIAL);
        return new Worker(
                1,
                ZonedDateTime.now(),
                "test_name",
                coords,
                44.55f,
                new Date(),
                org,
                LocalDateTime.now(),
                Position.CLEANER
        );
    }
}
