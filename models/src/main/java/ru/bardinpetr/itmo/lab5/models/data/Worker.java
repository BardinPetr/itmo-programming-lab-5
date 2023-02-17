package ru.bardinpetr.itmo.lab5.models.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.With;
import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

import java.time.LocalDateTime;
import java.util.Comparator;


@Data
@AllArgsConstructor
public class Worker implements Comparable<Worker>, IKeyedEntity<Long> {
    private static Long nextId = 0L;
    @With
    @NonNull
    private final Long id;
    @NonNull
    private final java.time.LocalDateTime creationDate;

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
        id = nextId++;
        creationDate = LocalDateTime.now();
    }

    @JsonIgnore
    @Override
    public Long getPrimaryKey() {
        return id;
    }

    @JsonIgnore
    @Override
    public void setPrimaryKey(Long key) {
//        this.id = key;
    }

    /**
     * @param worker the object to be compared.
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