package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.resonses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;

/**
 * Class of info command
 */
@Data
@NoArgsConstructor
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
    }
}
