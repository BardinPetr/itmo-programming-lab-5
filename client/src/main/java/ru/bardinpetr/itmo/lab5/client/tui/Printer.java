package ru.bardinpetr.itmo.lab5.client.tui;

public interface Printer {
    default void show(String s) {
    };

    default void suggestInput() {
    };

    default void showLine(String s) {
    };
}
