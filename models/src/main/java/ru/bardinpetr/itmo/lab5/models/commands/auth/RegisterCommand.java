package ru.bardinpetr.itmo.lab5.models.commands.auth;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;

@Data
public class RegisterCommand extends AuthCommand<DefaultAuthenticationCredentials> {
    @Override
    public String getType() {
        return "register";
    }
}
