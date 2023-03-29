package ru.bardinpetr.itmo.lab5.models.commands.api;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;

/**
 * Class of info command
 */
@Data
public class InfoCommand extends APICommand {
    @Override
    public String getType() {
        return "info";
    }

    @Override
    public InfoCommand.InfoCommandResponse createResponse() {
        return new InfoCommand.InfoCommandResponse();
    }

    public static class InfoCommandResponse implements APICommandResponse {
        private CollectionInfo result;

        public CollectionInfo getResult() {
            return result;
        }

        public void setResult(CollectionInfo result) {
            this.result = result;
        }

        @Override
        public String getUserMessage() {
            return "collection information\n" +
                    "   name:" + result.getName() +
                    ",\n   element type:" + result.getType() +
                    ",\n   initializationDate:" + result.getInitializationDate() +
                    ",\n   itemsCount:" + result.getItemsCount();
        }
    }
}