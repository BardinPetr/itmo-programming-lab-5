package ru.bardinpetr.itmo.lab5.models.commands.auth;

import lombok.Data;

@Data
public class RegisterCommand extends AuthCommand {
    @Override
    public String getType() {
        return "register";
    }
}
