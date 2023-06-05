package ru.bardinpetr.itmo.lab5.events.server.proxy;

import ru.bardinpetr.itmo.lab5.events.models.Event;

import java.lang.reflect.Method;
import java.util.function.BiFunction;

/**
 * Functional interface for marking proxied methods.
 * Return null not to mark event
 *
 * @param <T> proxied target object type
 */
public interface EventMarker extends BiFunction<Object, Method, Event> {
}
