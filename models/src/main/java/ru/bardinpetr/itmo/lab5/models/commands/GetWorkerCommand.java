package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of show command
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class GetWorkerCommand extends APICommand {
    @NonNull
    public Integer id;

    @Override
    public String getType() {
        return "gw";
    }

    @Override
    public GetWorkerCommandResponse createResponse() {
        return new GetWorkerCommandResponse();
    }

    @Data
    @NoArgsConstructor
    public static class GetWorkerCommandResponse implements ICommandResponse {
        @NonNull
        private Worker worker;
    }
}
