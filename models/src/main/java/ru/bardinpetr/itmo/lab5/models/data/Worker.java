package ru.bardinpetr.itmo.lab5.models.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;
import ru.bardinpetr.itmo.lab5.models.data.collection.IIdentifiableEntry;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;


@Data
@Jacksonized
public class Worker implements Comparable<Worker>, IIdentifiableEntry<Long> {
    private static Long nextId = 0L;
    @NonNull
    private Long id;
    @NonNull
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

    public Worker() {
    }

    public static WorkerBuilder builder() {
        return new WorkerBuilder();
    }

    @JsonIgnore
    @Override
    public Long getKey() {
        return id;
    }

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

    public static class WorkerBuilder {
        private final Worker worker = new Worker();

        public WorkerBuilder() {
            worker.id = Worker.nextId;
            Worker.nextId++;
        }

        public WorkerBuilder coordinates(Coordinates coordinates) {
            worker.coordinates = coordinates;
            return this;
        }

        public WorkerBuilder endDate(LocalDateTime localDateTime) {
            worker.endDate = localDateTime;
            return this;
        }

        public WorkerBuilder name(String name) {
            worker.name = name;
            return this;
        }

        public WorkerBuilder organization(Organization organization) {
            worker.organization = organization;
            return this;
        }

        public WorkerBuilder position(Position position) {
            worker.position = position;
            return this;
        }

        public WorkerBuilder salary(float salary) {
            worker.salary = salary;
            return this;
        }

        public WorkerBuilder startDate(Date from) {
            worker.startDate = from;
            return this;
        }

        public Worker build() {
            return worker;
        }


    }
}