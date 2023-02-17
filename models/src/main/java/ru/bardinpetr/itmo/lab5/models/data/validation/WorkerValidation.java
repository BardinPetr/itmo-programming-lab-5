package ru.bardinpetr.itmo.lab5.models.data.validation;

import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

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
    public static ValidationResponse validateId(Long id) {
        return (id <= 0) ?
                new ValidationResponse(false, "id must be greater than 0") :
                new ValidationResponse(true, "");
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
    public static ValidationResponse validateSalary(float salary) {
        return salary > 0 ?
                new ValidationResponse(true, "") :
                new ValidationResponse(false, "salary must be greater than 0");
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
