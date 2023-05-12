package ru.bardinpetr.itmo.lab5.server.auth.interfaces;

public interface IPasswordController {
    byte[] getHash(String password, String salt);

    boolean compare(byte[] first, byte[] second);

    String randomString(int length);

}
