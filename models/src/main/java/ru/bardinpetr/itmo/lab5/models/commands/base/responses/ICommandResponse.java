package ru.bardinpetr.itmo.lab5.models.commands.base.responses;

public interface ICommandResponse {
    default String getUserMessage() {
        return toString();
    }
}
