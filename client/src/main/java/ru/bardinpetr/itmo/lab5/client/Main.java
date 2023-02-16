package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.client.tui.ObjectScanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(ObjectScanner.scan(Worker.class).toString());
    }
}
