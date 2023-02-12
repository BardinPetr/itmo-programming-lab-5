package ru.bardinpetr.itmo.lab5.models.data;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.exceptions.WrongDataException;

import java.time.LocalDateTime;
import java.util.Comparator;

@Data
@Builder
public class Worker implements Comparable<Worker> {
    private static Long nextId = 0L;

    private final Long id = nextId++;
    private final java.time.LocalDateTime creationDate = LocalDateTime.now();

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

    public void setSalary(@NonNull Float salary) throws WrongDataException {
        if (salary < 0) throw new WrongDataException("salary must be greater than 0");
        this.salary = salary;
    }

    public void setName(String name) throws WrongDataException {
        if (name.isEmpty()) throw new WrongDataException("name must be not empty");
        this.name = name;
    }
}