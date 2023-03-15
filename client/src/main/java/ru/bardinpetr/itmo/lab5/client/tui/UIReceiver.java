package ru.bardinpetr.itmo.lab5.client.tui;

public interface UIReceiver {
    <T> T fill(Class<T> target);

    default <T> T fill(Class<T> target, T defaultObject) {
        return fill(target, null);
    }
}
