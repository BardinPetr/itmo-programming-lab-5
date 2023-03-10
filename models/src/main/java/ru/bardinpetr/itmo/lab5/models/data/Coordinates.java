package ru.bardinpetr.itmo.lab5.models.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

//import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
public class Coordinates implements Comparable<Coordinates> {
    @NonNull
    private Integer x;
    private float y;

    public Coordinates() {
    }

    /**
     * @param other the object to be compared.
     * @return {@literal <} 0 than other object is greater, {@literal >} 0 this object is greater
     */
    @Override
    public int compareTo(Coordinates other) {
        return (int) ((x + y) - (other.x + other.y));
    }

}
