package ru.bardinpetr.itmo.lab5.server.app.modules.events;

import ru.bardinpetr.itmo.lab5.events.server.storage.LocalEventStorage;
import ru.bardinpetr.itmo.lab5.server.app.utils.ServiceProvider;

public class EventFacade {

    public static EventApplication createApp() {
        var eventStorage = new LocalEventStorage();
        var proxy = new DBEventLoggerProxy(eventStorage);

        var service = ServiceProvider.getInstance();
        service.put("loggerProxyFactory", proxy);
        service.put("eventStorage", eventStorage);

        return new EventApplication(eventStorage);
    }
}
