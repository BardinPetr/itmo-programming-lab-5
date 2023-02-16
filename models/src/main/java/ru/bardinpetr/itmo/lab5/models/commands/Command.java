package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;

/**
 * General class for all commands
 */
@Data
public abstract class Command {
    public abstract String getType();

    public Field[] getInlineArgs() {
        return new Field[0];
    }
    public Field[] getInteractArgs() {
        return new Field[0];
    }

}
