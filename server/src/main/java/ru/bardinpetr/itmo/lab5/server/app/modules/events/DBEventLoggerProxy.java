package ru.bardinpetr.itmo.lab5.server.app.modules.events;

import ru.bardinpetr.itmo.lab5.common.utils.InterfaceUtils;
import ru.bardinpetr.itmo.lab5.db.backend.IDBDeleteDAO;
import ru.bardinpetr.itmo.lab5.db.backend.IDBInsertDAO;
import ru.bardinpetr.itmo.lab5.db.backend.IDBUpdateDAO;
import ru.bardinpetr.itmo.lab5.db.frontend.dao.ITableDAO;
import ru.bardinpetr.itmo.lab5.events.models.Event;
import ru.bardinpetr.itmo.lab5.events.server.proxy.EventMarker;
import ru.bardinpetr.itmo.lab5.events.server.proxy.LoggerProxyHandler;
import ru.bardinpetr.itmo.lab5.events.server.storage.IEventDestination;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class DBEventLoggerProxy implements EventMarker {

    private final IEventDestination storage;
    private final Map<String, Event.EventType> eventTypeMethodsMap;

    public DBEventLoggerProxy(IEventDestination storage) {
        this.storage = storage;

        eventTypeMethodsMap = new HashMap<>();
        for (var i : Map.of(
                Event.EventType.CREATE, IDBInsertDAO.class,
                Event.EventType.UPDATE, IDBUpdateDAO.class,
                Event.EventType.DELETE, IDBDeleteDAO.class
        ).entrySet())
            for (var m : InterfaceUtils.getInterfaceMethodsNames(i.getValue()))
                eventTypeMethodsMap.put(m, i.getKey());
    }

    @SuppressWarnings("unchecked")
    public <T> T wrap(Object original, Class<T> targetInterface) {
        return (T) Proxy.newProxyInstance(
                original.getClass().getClassLoader(),
                new Class[]{targetInterface},
                new LoggerProxyHandler<>(
                        original,
                        eventTypeMethodsMap.keySet(),
                        storage,
                        this
                )
        );
    }

    @Override
    public Event apply(Object target, Method method, Object[] args, Object result) {
        var type = eventTypeMethodsMap.get(method.getName());
        return new Event(
                type,
                ((ITableDAO) target).getTableName(),
                switch (type) {
                    case CREATE -> result;
                    case DELETE -> args.length == 0 ? null : args[0];
                    case UPDATE -> args[0];
                }
        );
    }
}
