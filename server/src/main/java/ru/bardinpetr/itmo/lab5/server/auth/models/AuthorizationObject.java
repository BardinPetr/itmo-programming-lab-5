package ru.bardinpetr.itmo.lab5.server.auth.models;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.server.auth.SHAPasswordController;
import ru.bardinpetr.itmo.lab5.server.auth.interfaces.IPasswordController;

import java.util.Arrays;

@Data
public class AuthorizationObject {
    private final String username;
    private final byte[] hashedPassword;
    private final String salt;
    private static IPasswordController pc = new SHAPasswordController();
    private int id = 0;

    public AuthorizationObject(int id, String username, byte[] hashedPassword, String salt) {
        this.id = id;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    public AuthorizationObject(String username, String password) {
        this.username = username;
        salt = pc.randomString(7);
        hashedPassword = pc.getHash(password, salt);
    }

    public boolean validate() {
        return getSalt().length() <= 50 && getUsername().length() <= 50;
    }

    @Override
    public String toString() {
        return "AuthorizationObject{" +
                "username='" + username + '\'' +
                ", hashedPassword=" + Arrays.toString(hashedPassword) +
                ", salt='" + salt + '\'' +
                '}';
    }
}
