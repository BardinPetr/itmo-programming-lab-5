package ru.bardinpetr.itmo.lab5.clientgui.utils;

import lombok.Getter;

import java.net.InetSocketAddress;

public class Config {

    private static final Config instance = new Config();
    @Getter
    private InetSocketAddress serverAddress;

    private Config() {
//        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
//        });

        var hostname = System.getProperty("serverHost", "localhost");
        var port = System.getProperty("serverPort", "5001");
        int intPort;
        try {
            intPort = Integer.parseInt(port);
        } catch (Exception ignored) {
            System.exit(0);
            return;
        }

        serverAddress = new InetSocketAddress(hostname, intPort);
    }

    public static Config getInstance() {
        return instance;
    }
}
