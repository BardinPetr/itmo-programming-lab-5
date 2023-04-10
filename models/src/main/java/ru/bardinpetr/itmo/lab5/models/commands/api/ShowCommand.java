package ru.bardinpetr.itmo.lab5.models.commands.api;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.requests.UserAPICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.ListAPICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of show command
 */
@Data
public class ShowCommand extends UserAPICommand {
    @Override
    public String getType() {
        return "show";
    }

    @Override
    public ShowCommandResponse createResponse() {
        return new ShowCommandResponse();
    }

    public static class ShowCommandResponse extends ListAPICommandResponse<Worker> {
        @Override
        public String getUserMessage() {
            var result = getResult();
            return "the whole collection" +
                    Worker.nicePrintFormat(result);
        }
    }
}
