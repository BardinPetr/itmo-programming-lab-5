package ru.bardinpetr.itmo.lab5.models.data.validation;

import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.util.Date;

/**
 * A class for worker's data validation
 */
public class WorkerValidation {

    /**
     * @param name Worker's name
     * @return response with error message
     */
    public static ValidationResponse validateName(String name) {
        return name.isEmpty() ?
                new ValidationResponse(false, "Name must not be empty") :
                new ValidationResponse(true, "");
    }

    /**
     * @param id Worker's id
     * @return response with error message
     */
    public static ValidationResponse validateId(Integer id) {
        return (id < 0) ?
                new ValidationResponse(false, "id must be greater than 0") :
                new ValidationResponse(true, "");
    }

    @SuppressWarnings("deprecation")
    public static ValidationResponse validateStartDate(Date startDate) {
        Date maxDate = new Date(10000 - 1900, 1, 1);
        Date minDate = new Date(1000 - 1900, 1, 1);
        if (
                startDate.before(maxDate) &&
                        startDate.after(minDate)
        ) return new ValidationResponse(true, "");
        else return new ValidationResponse(false, "Incorrect data format");
    }

    /**
     * @param coordinates Worker's coordinates
     * @return response with error message
     */
    public static ValidationResponse validateCoordinate(Coordinates coordinates) {
        return CoordinatesValidation.validateAll(coordinates);
    }

    /**
     * @param salary Worker's salary
     * @return response with error message
     */
    public static ValidationResponse validateSalary(Float salary) {
        return (salary > 0 && (salary < Float.MAX_VALUE) && (!salary.isInfinite())) ?
                new ValidationResponse(true, "") :
                new ValidationResponse(false, String.format("salary must be greater than 0 and less then %.1f", Float.MAX_VALUE));
    }

    /**
     * @param worker Worker object to check all fields
     * @return response with error message
     */
    public static ValidationResponse validateAll(Worker worker) {
        if (!validateId(worker.getId()).isAllowed()) return validateId(worker.getId());
        if (!validateName(worker.getName()).isAllowed()) return validateName(worker.getName());
        if (!validateCoordinate(worker.getCoordinates()).isAllowed())
            return validateCoordinate(worker.getCoordinates());
        if (!validateSalary(worker.getSalary()).isAllowed()) return validateSalary(worker.getSalary());
        return new ValidationResponse(true, "");

    }
}
