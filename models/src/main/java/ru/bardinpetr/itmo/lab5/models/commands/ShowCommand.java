package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.resonses.ICommandResponse;

/**
 * Class of show command
 */
@Data
public class ShowCommand extends Command {
    public final String TYPE = "show";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public ShowCommandResponse createResponse() {
        return new ShowCommandResponse();
    }

    @Data
    public static class ShowCommandResponse implements ICommandResponse {
        public String test;
    }
}
