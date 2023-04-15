package ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands;

import java.io.IOException;
import java.io.InputStream;

public class DELETE {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;

        int next = 0;
        do {
            next = in.read();
            System.out.println("Got " + next);
        } while (next != -1);
    }
}
