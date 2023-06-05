package ru.bardinpetr.itmo.lab5.client.api.events;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.events.client.PoolingEventSource;
import ru.bardinpetr.itmo.lab5.events.models.EventSet;
import ru.bardinpetr.itmo.lab5.models.commands.api.PoolEventsCommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

import java.time.Duration;
import java.time.Instant;

public class APIPoolingEventSource extends PoolingEventSource {

    public APIPoolingEventSource(APIClientConnector api, Duration poolDuration) {
        super(fromTime -> APIPoolingEventSource.pool(api, fromTime), poolDuration);
    }

    private static EventSet pool(APIClientConnector api, Instant from) {
        var cmd = new PoolEventsCommand(from);

        APICommandResponse res;
        try {
            res = api.call(cmd);
        } catch (APIClientException e) {
            return null;
        }

        if (!res.isSuccess())
            return null;

        return ((PoolEventsCommand.PoolEventsCommandResponse) res).getEvents();
    }
}
