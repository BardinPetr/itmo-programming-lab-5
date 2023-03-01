package ru.bardinpetr.itmo.lab5.models.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.With;
import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;


@Data
@AllArgsConstructor
public class Worker implements Comparable<Worker>, IKeyedEntity<Integer> {
    private static Integer nextId = 0;
    @With
    @NonNull
    private final Integer id;
    @NonNull
    private final java.time.ZonedDateTime creationDate;

    @NonNull
    private String name;
    @NonNull
    private Coordinates coordinates;
    private float salary;
    @NonNull
    private java.util.Date startDate;
    @NonNull
    private Organization organization;

    private java.time.LocalDateTime endDate;
    private Position position;

    public Worker() {
        id = nextId++;
        creationDate = ZonedDateTime.now();
    }

    public static Comparator<Worker> getComparator() {
        return Comparator
                .comparing(Worker::getOrganization)
                .thenComparing(Worker::getPosition, nullsLast(naturalOrder()))
                .thenComparing(Worker::getName)
                .thenComparing(Worker::getSalary)
                .thenComparing((x, y) -> -1 * x.startDate.compareTo(y.startDate))
                .thenComparing(Worker::getEndDate, nullsLast(naturalOrder()))
                .thenComparing(Worker::getCoordinates)
                .thenComparing(Worker::getCreationDate)
                .thenComparing(Worker::getId);
    }

    @JsonIgnore
    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    @JsonIgnore
    @Override
    public void setPrimaryKey(Integer key) {
//        this.id = key;
    }

    /**
     * @param worker the object to be compared.
     */
    public int compareTo(@NonNull Worker worker) {
        return getComparator().compare(this, worker);
    }

    public static String nicePrintFormat(List<Worker> list) {
        String s = "";
        for (var i : list) {
            String coordinates = "(x: " + i.getCoordinates().getX() + "," +
                    "y: " + i.getCoordinates().getX() + ')';
            String organization = String.format(
                    "(full name: %s, type: %s)",
                    i.getOrganization().getFullName(),
                    i.getOrganization().getType()
            );
            s += "\n\tid: " + i.getId() +
                    ",\n\t creationDate: " + i.getCreationDate() +
                    ",\n\t name: '" + i.getName() + '\'' +
                    ",\n\t coordinates: " + coordinates +
                    ",\n\t salary: " + i.getSalary() +
                    ",\n\t startDate: " + i.getStartDate() +
                    ",\n\t organization: " + organization +
                    ",\n\t endDate: " + i.getEndDate() +
                    ",\n\t position: " + i.getPosition();
        }
        return s;

    }
}