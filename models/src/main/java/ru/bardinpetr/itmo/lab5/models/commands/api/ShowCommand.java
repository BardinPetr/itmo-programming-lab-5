package ru.bardinpetr.itmo.lab5.models.commands.api;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.requests.PagingAPICommand;

/**
 * Class of show command
 */
@Data
public class ShowCommand extends PagingAPICommand {
    @Override
    public String getType() {
        return "show";
    }

    @Override
    public ShowCommandResponse createResponse() {
        return new ShowCommandResponse();
    }

    public static class ShowCommandResponse extends DefaultCollectionCommandResponse {
    }
}
