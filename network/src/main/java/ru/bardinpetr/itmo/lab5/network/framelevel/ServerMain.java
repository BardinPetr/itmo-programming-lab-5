package ru.bardinpetr.itmo.lab5.network.framelevel;

import java.io.IOException;


public class ServerMain {
    public static void main(String[] args) throws IOException {
        Frame frame = new Frame(131231, "as".getBytes());
        var bytes = frame.toBytes();

        System.out.println(Frame.fromBytes(bytes).equals(frame));
    }
}
