package ru.bardinpetr.itmo.lab5.models.data.validation;

import ru.bardinpetr.itmo.lab5.models.data.Coordinates;

/**
 * A class for coordinate's data validation
 */
public class CoordinatesValidator implements Validator {
    /**
     * @param x x coordinate
     * @return response with error message
     */
    public static ValidationResponse validateX(Integer x) {
        if (x == null) return new ValidationResponse(false, "WorkerInfoPanel.xCoordinate.null.text");
        return (x >= 773) ?
                new ValidationResponse(false, "WorkerInfoPanel.xCoordinate.notInRange.text") :
                new ValidationResponse(true, "");
    }

    public static ValidationResponse validateY(Float y) {
        if (y == null) return new ValidationResponse(false, "WorkerInfoPanel.xCoordinate.null.text");
        return (y <= -413) ?
                new ValidationResponse(false, "WorkerInfoPanel.yCoordinate.notInRange.text") :
                new ValidationResponse(true, "");
    }


    /**
     * @param coordinates Coordinates object
     * @return response with error message
     */
    public static ValidationResponse validateAll(Coordinates coordinates) {
        if (!validateX(coordinates.getX()).isAllowed()) return validateX(coordinates.getX());
        if (!validateY(coordinates.getY()).isAllowed()) return validateY(coordinates.getY());
        return new ValidationResponse(true, "");
    }
}
