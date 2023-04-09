package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

/**
 * General class for all commands
 */
@JsonIgnoreProperties({"type", "id", "inlineArgs", "interactArgs"})
@Data
public abstract class APICommand implements IIdentifiableCommand {

    @Override
    public String getCmdIdentifier() {
        return getClass().getCanonicalName();
    }

    public abstract String getType();

    /**
     * @return respond for certain command
     */
    public APICommandResponse createResponse() {
        return null;
    }


    /**
     * list for arguments which should be entered in line with command
     */
    public Field[] getInlineArgs() {
        return new Field[0];
    }

    /**
     * list for arguments which should be entered line by line
     */
    public Field[] getInteractArgs() {
        return new Field[0];
    }

    /**
     * validation method
     */
    public ValidationResponse validate() {
        return new ValidationResponse(true, "OK");
    }
}
