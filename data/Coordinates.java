package data;

import data.validationExceptions.WrongDataException;
import org.jetbrains.annotations.NotNull;

public class Coordinates implements Comparable<Coordinates>{
    private Integer x; //Значение поля должно быть больше -720, Поле не может быть null
    private int y;

    public void setX(@NotNull Integer x) throws WrongDataException {
        if (x <= -720) {
            throw new WrongDataException("X coordinates must be greater than -720");
        }
        this.x = x;
    }

    public int compareTo(Coordinates coor){
        return (x + y) - (coor.x + coor.y);
    }

    public void setY(int y) {
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
