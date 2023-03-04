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
    @With
    @NonNull
    private final java.time.ZonedDateTime creationDate;

    @NonNull
    private String name;
    @NonNull
    private Coordinates coordinates;
    private float salary;
    @NonNull
    private java.util.Date startDate;
    private Organization organization;

    private java.time.LocalDate endDate;
    private Position position;

    public Worker() {
        id = 0;
        creationDate = ZonedDateTime.now();//ZonedDateTime.of(2023, 10, 10, 12, 12, 12, 12, ZoneId.of("UTC"));//TODO correct
    }

    public static Comparator<Worker> getComparator() {
        return Comparator
                .comparing(Worker::getOrganization, nullsLast(naturalOrder()))
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

//    @JsonIgnore
//    @Override
//    public void setPrimaryKey(Integer key) {
//        this.id = key;
//    }

    /**
     * @param worker the object to be compared.
     */
    public int compareTo(@NonNull Worker worker) {
        return getComparator().compare(this, worker);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        if (Float.compare(worker.getSalary(), getSalary()) != 0) return false;
        if (!getId().equals(worker.getId())) return false;
        if (!getName().equals(worker.getName())) return false;
        if (!getCoordinates().equals(worker.getCoordinates())) return false;
        if (!getStartDate().equals(worker.getStartDate())) return false;
        if (getOrganization() != null ? !getOrganization().equals(worker.getOrganization()) : worker.getOrganization() != null)
            return false;
        if (getEndDate() != null ? !getEndDate().equals(worker.getEndDate()) : worker.getEndDate() != null)
            return false;
        return getPosition() == worker.getPosition();
    }


    public static String nicePrintFormat(List<Worker> list) {
        String s = "";
        for (var i : list) {
            String coordinates = "(x: " + i.getCoordinates().getX() + "," +
                    "y: " + i.getCoordinates().getX() + ')';
            String organization = "";
            if (i.getOrganization() != null) {
                organization = String.format(
                        "(full name: %s, type: %s)",
                        i.getOrganization().getFullName(),
                        i.getOrganization().getType()
                );
            } else organization = "null";
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