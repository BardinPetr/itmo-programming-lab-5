package ru.bardinpetr.itmo.lab5.models.commands.api;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.requests.UserAPICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.ListAPICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of print_descending command
 */
@Data
public class PrintDescendingCommand extends UserAPICommand {

    @Override
    public String getType() {
        return "print_descending";
    }

    @Override
    public PrintDescendingCommandResponse createResponse() {
        return new PrintDescendingCommandResponse();
    }

    public static class PrintDescendingCommandResponse extends ListAPICommandResponse<Worker> {
        @Override
        public String getUserMessage() {
            var result = getResult();
            return "collection elements in descending order" +
                    Worker.nicePrintFormat(result);
        }
    }
}
