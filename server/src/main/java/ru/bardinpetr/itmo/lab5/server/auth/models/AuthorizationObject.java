package ru.bardinpetr.itmo.lab5.server.auth.models;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.server.auth.SHAPasswordController;
import ru.bardinpetr.itmo.lab5.server.auth.interfaces.IPasswordController;

@Data
public class AuthorizationObject {
    private final String username;
    private final byte[] hashedPassword;
    private final String salt;
    IPasswordController pc = new SHAPasswordController();

    public AuthorizationObject(String username, String password) {
        this.username = username;
        salt = pc.randomString(7);
        hashedPassword = pc.getHash(password, salt);
    }

}
