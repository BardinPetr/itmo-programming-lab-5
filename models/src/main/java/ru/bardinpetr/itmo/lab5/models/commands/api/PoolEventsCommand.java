package ru.bardinpetr.itmo.lab5.models.commands.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.events.models.EventSet;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoolEventsCommand extends APICommand {
    private Instant fromTime;

    @Override
    public PoolEventsCommandResponse createResponse() {
        return new PoolEventsCommandResponse();
    }

    @Data
    @NoArgsConstructor
    public static class PoolEventsCommandResponse extends APICommandResponse {
        private EventSet events;
    }
}
