package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;

/**
 * Class of info command
 */
@Data
public class InfoCommand extends Command {
    @Override
    public String getType() {
        return "info";
    }

    @Override
    public InfoCommand.InfoCommandResponse createResponse() {
        return new InfoCommand.InfoCommandResponse();
    }
    public static class InfoCommandResponse implements ICommandResponse {
        private CollectionInfo result;

        public CollectionInfo getResult() {
            return result;
        }

        public void setResult(CollectionInfo result) {
            this.result = result;
        }

        @Override
        public String getUserMessage() {
            return result.toString();
        }
    }
}
