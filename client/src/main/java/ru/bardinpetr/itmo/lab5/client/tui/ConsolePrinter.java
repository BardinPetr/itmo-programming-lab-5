package ru.bardinpetr.itmo.lab5.client.tui;

public class ConsolePrinter implements View {
    public void show(String str) {
        System.out.println(str);
    }

    public void showLine(String str) {
        System.out.print(str);
    }
}
