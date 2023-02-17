package ru.bardinpetr.itmo.lab5.models.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

import java.time.LocalDateTime;
import java.util.Comparator;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker implements Comparable<Worker>, IKeyedEntity<Long> {
    private static Long nextId = 0L;
    @NonNull
    private final Long id = nextId++;
    @NonNull
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

//    public static WorkerBuilder builder() {
//        return new WorkerBuilder();
//    }

    @JsonIgnore
    @Override
    public Long getPrimaryKey() {
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

//    public static class WorkerBuilder {
//        @NonNull
//        private String name;
//        @NonNull
//        private Coordinates coordinates;
//        @NonNull
//        private Float salary;
//        @NonNull
//        private java.util.Date startDate;
//        @NonNull
//        private Organization organization;
//
//        private java.time.LocalDateTime endDate;
//        private Position position;
//
//
//        public WorkerBuilder coordinates(Coordinates coordinates) {
//            this.coordinates = coordinates;
//            return this;
//        }
//
//        public WorkerBuilder endDate(LocalDateTime localDateTime) {
//            this.endDate = localDateTime;
//            return this;
//        }
//
//        public WorkerBuilder name(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public WorkerBuilder organization(Organization organization) {
//            this.organization = organization;
//            return this;
//        }
//
//        public WorkerBuilder position(Position position) {
//            this.position = position;
//            return this;
//        }
//
//        public WorkerBuilder salary(float salary) {
//            this.salary = salary;
//            return this;
//        }
//
//        public WorkerBuilder startDate(Date from) {
//            this.startDate = from;
//            return this;
//        }
//
//        public Worker build() {
//            return new Worker(
//                    name,
//                    coordinates,
//                    salary,
//                    startDate,
//                    organization,
//                    endDate,
//                    position
//                    );
//        }
//
//
//    }
}