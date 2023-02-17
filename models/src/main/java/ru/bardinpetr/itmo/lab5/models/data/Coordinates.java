package ru.bardinpetr.itmo.lab5.models.data;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

//import javax.validation.constraints.Min;

@Data
@Builder
@Jacksonized
public class Coordinates implements Comparable<Coordinates> {
    @NonNull
//    @Min(-719)
    private Integer x; // Значение поля должно быть больше -720
    private int y;

    /**
     * @param other the object to be compared.
     * @return < 0 than other object is greater, > 0 this object is greater
     */
    @Override
    public int compareTo(Coordinates other) {
        return (x + y) - (other.x + other.y);
    }

}
