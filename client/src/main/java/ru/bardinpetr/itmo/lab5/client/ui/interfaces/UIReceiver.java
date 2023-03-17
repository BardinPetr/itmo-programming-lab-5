package ru.bardinpetr.itmo.lab5.client.ui.interfaces;

public interface UIReceiver extends UIInputReceiver {
    void display(String text);

    void ok();

    void error(String message);
}
