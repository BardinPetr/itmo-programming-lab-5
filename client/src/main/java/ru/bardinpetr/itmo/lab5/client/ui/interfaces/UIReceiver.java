package ru.bardinpetr.itmo.lab5.client.ui.interfaces;

public interface UIReceiver extends UIInputReceiver {
    void display(String text);

    void interactSuggestion();

    void ok();

    void ok(String cmd);

    void error(String message);
}
