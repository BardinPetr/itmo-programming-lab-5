package ru.bardinpetr.itmo.lab5.models.commands.auth.models;

import lombok.Data;

@Data
public class AuthenticationCredentials {

    /**
     * Method to get credentials representation which do not contain sensitive data
     */
    public String safeIdentifier() {
        return toString();
    }
}
