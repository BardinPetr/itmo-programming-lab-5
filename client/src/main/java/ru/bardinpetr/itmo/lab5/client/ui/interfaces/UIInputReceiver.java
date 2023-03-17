package ru.bardinpetr.itmo.lab5.client.ui.interfaces;

public interface UIInputReceiver {

    default <T> T fill(Class<T> target) {
        return fill(target, null);
    }

    <T> T fill(Class<T> target, T defaultObject);

    boolean hasNextLine();

    String nextLine();
}
