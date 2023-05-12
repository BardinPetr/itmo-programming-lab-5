package ru.bardinpetr.itmo.lab5.server.db.utils;

import lombok.Data;

@Data
public class BasicAuthProvider implements DBAuthProvider {
    private final String username;
    private final String password;
}
