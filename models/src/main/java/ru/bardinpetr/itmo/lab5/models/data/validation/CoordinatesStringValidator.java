package ru.bardinpetr.itmo.lab5.models.data.validation;

public class CoordinatesStringValidator extends CoordinatesValidator {
    public ValidationResponse validateX(String x) {
        Integer X;
        try {
            X = Integer.valueOf(x);
        } catch (NumberFormatException e) {
            return new ValidationResponse(false, "WorkerInfoPanel.xCoordinate.badFormat.text");
        }
        return validateX(X);
    }

    public ValidationResponse validateY(String y) {
        Float Y;
        try {
            Y = Float.valueOf(y);
        } catch (NumberFormatException e) {
            return new ValidationResponse(false, "WorkerInfoPanel.yCoordinate.badFormat.text");
        }
        return validateY(Y);
    }

    }
