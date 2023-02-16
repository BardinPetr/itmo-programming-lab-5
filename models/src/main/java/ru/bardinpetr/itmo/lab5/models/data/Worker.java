package ru.bardinpetr.itmo.lab5.models.data;

import lombok.*;
import lombok.extern.jackson.Jacksonized;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Data
@Jacksonized
public class Worker implements Comparable<Worker> {

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
    
    public Worker(){};

    public static workerBuilder builder(){
        return new workerBuilder();
    }
    public static class workerBuilder{
        private Worker worker= new Worker();
        public workerBuilder(){
            worker.id = Worker.nextId;
            Worker.nextId++;
        }

        public workerBuilder coordinates(Coordinates coordinates){
            worker.coordinates = coordinates;
            return this;
        }
        public workerBuilder endDate(LocalDateTime localDateTime){
            worker.endDate = localDateTime;
            return this;
        }
        public workerBuilder name(String name){
            worker.name = name;
            return this;
        }

        public workerBuilder organization(Organization organization){
            worker.organization = organization;
            return this;
        }

        public workerBuilder position(Position position){
            worker.position = position;
            return this;
        }

        public workerBuilder salary(float salary){
            worker.salary = salary;
            return this;
        }

        public workerBuilder startDate(Date from){
            worker.startDate = from;
            return this;
        }

        public Worker build(){
            return worker;
        }






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
}