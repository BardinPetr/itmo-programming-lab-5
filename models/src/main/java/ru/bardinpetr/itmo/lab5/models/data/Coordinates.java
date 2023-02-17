package ru.bardinpetr.itmo.lab5.models.data;

import lombok.Data;
import lombok.NonNull;

//import javax.validation.constraints.Min;

@Data
public class Coordinates implements Comparable<Coordinates> {
    @NonNull
    private Integer x; // Значение поля должно быть больше -720
    private int y;

    public Coordinates() {
    }

    /**
     * @param other the object to be compared.
     * @return < 0 than other object is greater, > 0 this object is greater
     */
    @Override
    public int compareTo(Coordinates other) {
        return (x + y) - (other.x + other.y);
    }
    
}
