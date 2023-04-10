package ru.bardinpetr.itmo.lab5.models.commands.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

/**
 * General class for all commands
 */
@JsonIgnoreProperties({"type", "id", "inlineArgs", "interactArgs"})
@Data
public abstract class APICommand implements IIdentifiableCommand {

    @Override
    public String getCmdIdentifier() {
        return getClass().getSimpleName();
    }

    /**
     * @return respond for certain command
     */
    public APICommandResponse createResponse() {
        return null;
    }

    /**
     * validation method
     */
    public ValidationResponse validate() {
        return new ValidationResponse(true, "OK");
    }
}
