package ru.bardinpetr.itmo.lab5.events.server.proxy;


import ru.bardinpetr.itmo.lab5.events.server.storage.IEventDestination;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class LoggerProxyHandler<K> implements InvocationHandler {
    private final Set<String> methodWhitelist;
    private final IEventDestination storage;
    private final EventMarker marker;
    private final K target;

    public LoggerProxyHandler(K target, Collection<String> methodWhitelist, IEventDestination storage, EventMarker marker) {
        this.methodWhitelist = new HashSet<>(methodWhitelist);
        this.target = target;
        this.storage = storage;
        this.marker = marker;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (methodWhitelist.contains(method.getName())) {
            var event = marker.apply(target, method);
            if (event != null)
                storage.insert(event);
        }

        return method.invoke(target, args);
    }
}
