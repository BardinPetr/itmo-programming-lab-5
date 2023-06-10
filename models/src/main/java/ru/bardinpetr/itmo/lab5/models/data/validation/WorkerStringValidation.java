package ru.bardinpetr.itmo.lab5.models.data.validation;

public class WorkerStringValidation extends WorkerValidator {
    public ValidationResponse validateName(String name) {
        return super.validateName(name);
    }

    public ValidationResponse validateSalary(String salary) {
        Float fsalary;
        try {
            fsalary = Float.valueOf(salary);
        } catch (NumberFormatException e) {
            return new ValidationResponse(false, "WorkerInfoPanel.workerSalary.badFormat.text");
        }
        return super.validateSalary(fsalary);
    }
}