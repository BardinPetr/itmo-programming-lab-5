package ru.bardinpetr.itmo.lab5.events.server.proxy;

import ru.bardinpetr.itmo.lab5.events.models.Event;

import java.lang.reflect.Method;

/**
 * Functional interface for marking proxied methods.
 * Return null not to mark event
 */
public interface EventMarker {
    Event apply(Object target, Method method, Object[] args, Object result);
}
