package ru.bardinpetr.itmo.lab5.models.commands.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.IAPIMessage;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

/**
 * General class for all commands
 */
@JsonIgnoreProperties({"type", "cmdIdentifier", "inlineArgs", "interactArgs"})
@Data
@NoArgsConstructor
public abstract class APICommand implements IIdentifiableCommand<Class<? extends APICommand>>, IAPIMessage {

    @Override
    public Class<? extends APICommand> getCmdIdentifier() {
        return getClass();
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
