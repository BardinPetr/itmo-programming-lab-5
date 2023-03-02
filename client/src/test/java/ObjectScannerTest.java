import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.tui.ObjectScanner;
import ru.bardinpetr.itmo.lab5.client.tui.View;
import ru.bardinpetr.itmo.lab5.common.serdes.ObjectMapperFactory;
import ru.bardinpetr.itmo.lab5.models.data.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectScannerTest {

    private ObjectScanner getScanner(String string) {
        Scanner scanner = new Scanner(string);
        return new ObjectScanner(
                scanner,
                new View() {
                },
                ObjectMapperFactory.createMapper()
        );

    }

    @DisplayName("Coordinates scanner")
    @Test
    void coordinatesScanTest() {
        assertDoesNotThrow(() -> {
            assertEquals(
                    new Coordinates(100, 100),
                    getScanner("""
                            100
                            100""").scan(Coordinates.class),
                    "Correct data"
            );
        });

        assertThrows(ParserException.class, () -> {
            getScanner("""
                    1000
                    100""").scan(Coordinates.class);
        });

        assertThrows(ParserException.class, () -> {
            getScanner("""
                    100
                    -1000""").scan(Coordinates.class);
        });

        assertThrows(ParserException.class, () -> {
            getScanner("""
                    adadaf
                    -100""").scan(Coordinates.class);
        });

        assertThrows(ParserException.class, () -> {
            getScanner("""
                    100
                    acas""").scan(Coordinates.class);
        });

        assertThrows(ParserException.class, () -> {
            getScanner("""
                                                
                    100""").scan(Coordinates.class);
        });

        assertThrows(ParserException.class, () -> {
            getScanner("""
                    100
                                                
                    """).scan(Coordinates.class);
        });

    }


    @DisplayName("Organization scanner")
    @Test
    void organizationScanTest() {
        assertDoesNotThrow(() -> {
            assertEquals(
                    new Organization("ITMO", OrganizationType.PUBLIC),
                    getScanner("""
                            ITMO
                            PUBLIC""").scan(Organization.class),
                    "Correct data"
            );
        });
        assertThrows(ParserException.class, () ->
        {
            getScanner("""
                                                
                    PUBLIC""").scan(Organization.class);
        }, "Name can't be null");

        assertThrows(ParserException.class, () ->
        {
            getScanner("""
                    ITMO
                    PUBLI2C""").scan(Organization.class);
        }, "Type should be from enum list");

        assertThrows(NoSuchElementException.class, () ->
        {
            getScanner("""
                    ITMO
                    """).scan(Organization.class);
        }, "Type should not be null");
    }

    @DisplayName("Worker scanner")
    @Test
    void workerScannerTest() {
        assertDoesNotThrow(() -> {
            assertEquals(
                    getScanner("""
                            Artem
                            12.3
                            02-03-2023
                            02-03-2023
                            13
                            12
                            ITMO
                            PUBLIC
                            CLEANER""").scan(Worker.class),
                    new Worker(
                            0,
                            ZonedDateTime.now(),
                            "Artem",
                            new Coordinates(13, 12),
                            12.3f,
                            new Date(2023 - 1900, Calendar.MARCH, 2),
                            new Organization("ITMO", OrganizationType.PUBLIC),
                            LocalDate.of(2023, Month.MARCH, 2),
                            Position.CLEANER
                    ),
                    "Correct data"
            );
        });

        assertThrows(NullPointerException.class,
                () -> {
                    getScanner("""
                                                            
                            12.3
                            02-03-2023
                            02-03-2023
                            13
                            12
                            ITMO
                            PUBLIC
                            CLEANER""").scan(Worker.class);
                },
                "Name can not be null");

        assertThrows(ParserException.class,
                () -> {
                    getScanner("""
                            Artem
                            12ds
                            02-03-2023
                            02-03-2023
                            13
                            12
                            ITMO
                            PUBLIC
                            CLEANER""").scan(Worker.class);
                },
                "Salary must be float");

        assertThrows(ParserException.class,
                () -> {
                    getScanner("""
                            Artem
                                                            
                            02-03-2023
                            02-03-2023
                            13
                            12
                            ITMO
                            PUBLIC
                            CLEANER""").scan(Worker.class);
                },
                "Salary can not be null");
    }

}
