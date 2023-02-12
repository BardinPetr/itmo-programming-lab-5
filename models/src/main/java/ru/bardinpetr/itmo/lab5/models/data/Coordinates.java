package ru.bardinpetr.itmo.lab5.models.data;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.exceptions.WrongDataException;

//import javax.validation.constraints.Min;

@Data
@Builder
public class Coordinates implements Comparable<Coordinates> {
    @NonNull
//    @Min(-719)
    private Integer x; // Значение поля должно быть больше -720
    private int y;

    @Override
    public int compareTo(Coordinates other) {
        return (x + y) - (other.x + other.y);
    }

    public void setX(Integer x) throws WrongDataException {
        if (x <= -720) throw new WrongDataException("X coordinates must be greater than -720");
        this.x = x;
    }
}
