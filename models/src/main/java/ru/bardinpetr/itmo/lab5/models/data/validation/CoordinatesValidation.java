package ru.bardinpetr.itmo.lab5.models.data.validation;

import ru.bardinpetr.itmo.lab5.models.data.Coordinates;

/**
 * A class for coordinate's data validation
 */
public class CoordinatesValidation {
    /**
     * @param x x coordinate
     * @return response with error message
     */
    public static ValidationResponse validateX(Integer x) {
        return (x <= -720) ?
                new ValidationResponse(false, "X coordinate must be greater than -720") :
                new ValidationResponse(true, "");
    }

    /**
     * @param coordinates Coordinates object
     * @return response with error message
     */
    public static ValidationResponse validateAll(Coordinates coordinates) {
        return validateX(coordinates.getX());
    }
}
