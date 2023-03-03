package ru.bardinpetr.itmo.lab5.common.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bardinpetr.itmo.lab5.common.serdes.XMLSerDesService;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.bardinpetr.itmo.lab5.common.tests.utils.WorkerGenerator.generateWorker;

public class SerDesTest {

    @Test
    @DisplayName("Deserialization of serialized Worker object should succeed and return the same object")
    void workerSerializationTest() {
        var worker = generateWorker();

        var service = new XMLSerDesService<>(Worker.class);
        assertDoesNotThrow(() -> {
            var serialized = service.serialize(worker);
            var deserialized = service.deserialize(serialized);

            assertEquals(worker,
                    deserialized.withCreationDate(
                            deserialized
                                    .getCreationDate()
                                    .withZoneSameLocal(worker.getCreationDate().getZone())
                    )
            );
        });
    }
}
