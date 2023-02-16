package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.resonses.ICommandResponse;

/**
 * General class for all commands
 */
@Data
public abstract class Command {
    public abstract String getType();

    public ICommandResponse createResponse() {
        return null;
    }

    public Field[] getInlineArgs() {
        return new Field[0];
    }
    public Field[] getInteractArgs() {
        return new Field[0];
    }

}
