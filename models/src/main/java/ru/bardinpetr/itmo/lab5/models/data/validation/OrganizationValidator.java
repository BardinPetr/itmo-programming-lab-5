package ru.bardinpetr.itmo.lab5.models.data.validation;

import ru.bardinpetr.itmo.lab5.models.data.Organization;

/**
 * A class for organisation's data validation
 */
public class OrganizationValidator implements Validator {

    /**
     * @param s x coordinate
     * @return response with error message
     */
    public static ValidationResponse validateFullName(String s) {
        if (s == null) {
            return new ValidationResponse(false, "OrganizationValidator.workerName.null.text");
        } else {
            return new ValidationResponse(true, "");
        }
    }


    /**
     * @param organization Coordinates object
     * @return response with error message
     */
    public static ValidationResponse validateAll(Organization organization) {
        if (organization==null) return new ValidationResponse(true, "");
        return validateFullName(organization.getFullName());
    }
}

