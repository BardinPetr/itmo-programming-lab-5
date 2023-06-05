package ru.bardinpetr.itmo.lab5.server.app.utils;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {

    private static ServiceProvider instance;
    private final Map<String, Object> objects = new HashMap<>();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        if (instance == null)
            instance = new ServiceProvider();
        return instance;
    }

    public void put(String name, Object value) {
        objects.put(name, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> cls, String name) {
        return (T) objects.get(name);
    }
}
