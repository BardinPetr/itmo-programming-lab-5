package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
/**
 * General class for all commands
 */
@Data
public class Command {
    public final String TYPE = "CMD";

    public Command(){
    }
}
