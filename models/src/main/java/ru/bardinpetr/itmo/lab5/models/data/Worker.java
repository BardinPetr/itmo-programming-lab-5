package ru.bardinpetr.itmo.lab5.models.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;
import ru.bardinpetr.itmo.lab5.models.data.exception.WrongDataException;

import java.time.LocalDateTime;
import java.util.Comparator;


@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class Worker implements Comparable<Worker> {
    private static Long nextId = 0L;
    @NonNull
    @Builder.Default
    private Long id = nextId++;
    @NonNull
    @Builder.Default
    private java.time.LocalDateTime creationDate = LocalDateTime.now();

    @NonNull
    private String name;
    @NonNull
    private Coordinates coordinates;
    @NonNull
    private Float salary;
    @NonNull
    private java.util.Date startDate;
    @NonNull
    private Organization organization;

    private java.time.LocalDateTime endDate;
    private Position position;

    /**
     * @param worker the object to be compared.
     * @return
     */
    public int compareTo(@NonNull Worker worker) {
        return Comparator
                .nullsLast(
                        Comparator
                                .comparing(Worker::getOrganization)
                                .thenComparing(Worker::getPosition)
                                .thenComparing(Worker::getName)
                                .thenComparing(Worker::getSalary)
                                .thenComparing((x, y) -> -1 * x.startDate.compareTo(y.startDate))
                                .thenComparing(Worker::getEndDate)
                                .thenComparing(Worker::getCoordinates)
                                .thenComparing(Worker::getCreationDate)
                                .thenComparing(Worker::getId)
                ).compare(this, worker);
    }
}