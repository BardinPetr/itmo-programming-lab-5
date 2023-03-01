package ru.bardinpetr.itmo.lab5.client.tui;

public interface View {
    void show(String s);

    void suggestInput();

    void showLine(String s);
}
