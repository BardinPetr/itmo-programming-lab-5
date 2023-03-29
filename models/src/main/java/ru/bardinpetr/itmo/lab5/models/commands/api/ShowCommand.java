package ru.bardinpetr.itmo.lab5.models.commands.api;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.ListCommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of show command
 */
@Data
public class ShowCommand extends APICommand {
    @Override
    public String getType() {
        return "show";
    }

    @Override
    public ShowCommandResponse createResponse() {
        return new ShowCommandResponse();
    }

    public static class ShowCommandResponse extends ListCommandResponse<Worker> {
        @Override
        public String getUserMessage() {
            var result = getResult();
            return "the whole collection" +
                    Worker.nicePrintFormat(result);
        }
    }
}
