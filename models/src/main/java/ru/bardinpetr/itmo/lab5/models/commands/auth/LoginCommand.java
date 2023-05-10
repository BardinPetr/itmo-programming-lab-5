package ru.bardinpetr.itmo.lab5.models.commands.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Command for performing login
 */
@Data
@RequiredArgsConstructor
public class LoginCommand extends AuthCommand {
    @Override
    public String getType() {
        return "login";
    }
}
