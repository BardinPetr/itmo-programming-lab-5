package ru.bardinpetr.itmo.lab5.server.db.dto;

import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Position;

public record WorkerDTO(
        Integer id,
        Integer ownerId,
        Integer organizationId,
        java.time.ZonedDateTime creationDate,
        java.util.Date startDate,
        java.time.LocalDate endDate,
        String name,
        Float salary,
        Coordinates coordinates,
        Position position
) {
}
