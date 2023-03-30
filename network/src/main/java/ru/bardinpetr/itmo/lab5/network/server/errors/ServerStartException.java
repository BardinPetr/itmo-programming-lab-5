package ru.bardinpetr.itmo.lab5.network.server.errors;

import java.io.IOException;

public class ServerStartException extends RuntimeException {
    public ServerStartException(IOException e) {
        super("Could not start server", e);
    }

    public ServerStartException(String msg) {
        super("Could not start server: %s".formatted(msg));
    }
}
