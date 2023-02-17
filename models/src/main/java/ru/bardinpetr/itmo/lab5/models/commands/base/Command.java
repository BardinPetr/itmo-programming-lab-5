package ru.bardinpetr.itmo.lab5.models.commands.base;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.resonses.ICommandResponse;

/**
 * General class for all commands
 */
@Data
public abstract class Command {
    public abstract String getType();

    public ICommandResponse createResponse() {
        return null;
    }
}
