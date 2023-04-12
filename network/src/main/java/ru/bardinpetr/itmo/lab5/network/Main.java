package ru.bardinpetr.itmo.lab5.network;

import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        SetupJUL.loadProperties(Main.class);
    }
}
