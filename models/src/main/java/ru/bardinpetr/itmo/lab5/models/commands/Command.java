package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
/**
 * General class for all commands
 */
@Data
public abstract class Command {
    public abstract String getType();
}
