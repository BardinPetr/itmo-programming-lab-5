package data;

import data.validationExceptions.WrongDataException;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;

public class Worker implements Comparable<Worker>{
    private static Long nextId = (long) 8457038;


    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float salary; //Поле не может быть null, Значение поля должно быть больше 0
    private java.util.Date startDate; //Поле не может быть null
    private java.time.LocalDateTime endDate; //Поле может быть null
    private Position position; //Поле может быть null
    private Organization organization; //Поле не может быть null

    public Worker(){
        this.id = nextId;
        nextId+=234;
    }

    public int compareTo(Worker worker){
        return Comparator.nullsLast(
                Comparator.comparing(Worker::getOrganization)
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(@NotNull Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@NotNull LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(@NotNull Float salary) throws WrongDataException {
        if (salary < 0) throw new WrongDataException("salary must be greater than 0");
        this.salary = salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull Date startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(@NotNull Organization organization) {
        this.organization = organization;
    }



}