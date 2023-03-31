package ru.bardinpetr.itmo.lab5.network.serdes;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;

import java.io.*;

public class SocketMessageSerDes {
    public static byte[] serialize(SocketMessage obj) {
        ObjectOutputStream objStream;
        var target = new ByteArrayOutputStream(512);
        try {
            objStream = new ObjectOutputStream(target);
            objStream.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return target.toByteArray();
    }

    public static SocketMessage deserialize(byte[] bytes) {
        ObjectInputStream objStream;
        var target = new ByteArrayInputStream(bytes);
        SocketMessage resp;
        try {
            objStream = new ObjectInputStream(target);
            resp = (SocketMessage) objStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resp;
    }

}
