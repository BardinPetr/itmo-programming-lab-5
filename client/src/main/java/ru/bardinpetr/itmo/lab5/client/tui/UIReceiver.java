package ru.bardinpetr.itmo.lab5.client.tui;

public interface UIReceiver {
    default <T> T fill(Class<T> target) {
        return fill(target, null);
    }

    <T> T fill(Class<T> target, T defaultObject);

    void ok();

    void error(String message);

    void print(String s);
}
