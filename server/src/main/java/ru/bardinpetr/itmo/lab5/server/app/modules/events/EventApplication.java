package ru.bardinpetr.itmo.lab5.server.app.modules.events;

import ru.bardinpetr.itmo.lab5.events.server.storage.IEventStorage;
import ru.bardinpetr.itmo.lab5.models.commands.api.PoolEventsCommand;
import ru.bardinpetr.itmo.lab5.network.app.server.AbstractApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.models.requests.AppRequest;

public class EventApplication extends AbstractApplication {

    private final IEventStorage eventStorage;

    public EventApplication(IEventStorage eventStorage) {
        this.eventStorage = eventStorage;

        on(PoolEventsCommand.class, this::pool);
    }

    private void pool(AppRequest request) {
        var cmd = (PoolEventsCommand) request.payload();

        var eventSet = eventStorage.retrieve(cmd.getFromTime());
        var resp = cmd.createResponse();
        resp.setEvents(eventSet);

        request
                .response()
                .from(resp)
                .send();
    }
}
