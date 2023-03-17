package ru.bardinpetr.itmo.lab5.models.commands.base;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

/**
 * General class for all commands
 */
@Data
public abstract class APICommand {
    public abstract String getType();

    /**
     * @return respond for certain command
     */
    public ICommandResponse createResponse() {
        return null;
    }

    /**
     * list for arguments which should be entered in line with command
     *
     * @return
     */
    public Field[] getInlineArgs() {
        return new Field[0];
    }

    /**
     * list for arguments which should be entered line by line
     *
     * @return
     */
    public Field[] getInteractArgs() {
        return new Field[0];
    }

    /**
     * validation method
     *
     * @return
     */
    public ValidationResponse validate() {
        return new ValidationResponse(true, "OK");
    }
}
