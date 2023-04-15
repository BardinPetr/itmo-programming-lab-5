package ru.bardinpetr.itmo.lab5.models.commands.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.requests.PagingAPICommand;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of print_descending command
 */
@Data
@AllArgsConstructor
public class PrintDescendingCommand extends PagingAPICommand {
    @Override
    public String getType() {
        return "print_descending";
    }

    @Override
    public PrintDescendingCommandResponse createResponse() {
        return new PrintDescendingCommandResponse();
    }

    public static class PrintDescendingCommandResponse extends DefaultCollectionCommandResponse {
        @Override
        public String getUserMessage() {
            var result = getResult();
            return "collection elements in descending order" +
                    Worker.nicePrintFormat(result);
        }
    }
}
