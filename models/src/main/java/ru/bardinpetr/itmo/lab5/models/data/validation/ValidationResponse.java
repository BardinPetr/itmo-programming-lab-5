package ru.bardinpetr.itmo.lab5.models.data.validation;

import lombok.*;

/**
 * Class for validation response
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class ValidationResponse {
    /**
     * Was the validation passed successfully?
     */
    private boolean allowed;
    /**
     * msg for data errors
     */
    private String msg;

}
