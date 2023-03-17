package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ListCommandResponse;

/**
 * Class of show command
 */
@Data
public class GetWorkerIdsCommand extends APICommand {
    @Override
    public String getType() {
        return "gwi";
    }

    @Override
    public GetWorkerIdsCommandResponse createResponse() {
        return new GetWorkerIdsCommandResponse();
    }

    public static class GetWorkerIdsCommandResponse extends ListCommandResponse<Integer> {
    }
}