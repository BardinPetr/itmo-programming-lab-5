package ru.bardinpetr.itmo.lab5.models.commands.requests;

import lombok.Data;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.responses.ListAPICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of show command
 */
@Data
public class PagingAPICommand extends UserAPICommand {
    @NonNull
    public Integer offset = 0;

    @NonNull
    public Integer count = -1;

    @Override
    public String getType() {
        return "null";
    }

    @Override
    public DefaultCollectionCommandResponse createResponse() {
        return new DefaultCollectionCommandResponse();
    }

    public static class DefaultCollectionCommandResponse extends ListAPICommandResponse<Worker> {
        @Override
        public String getUserMessage() {
            var result = getResult();
            return "the whole collection" +
                    Worker.nicePrintFormat(result);
        }
    }
}
