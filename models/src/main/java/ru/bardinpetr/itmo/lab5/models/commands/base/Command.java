package ru.bardinpetr.itmo.lab5.models.commands.base;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

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

    public ValidationResponse validate() {
        return new ValidationResponse(true, "OK");
    }
}
