package ru.bardinpetr.itmo.lab5.models.data.validation;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Class for validation response
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ValidationResponse {
    /**
     * Was the validation passed successfully?
     */
    public boolean allowed;
    /**
     * msg for data errors
     */
    public String msg;

}
