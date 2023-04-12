package ru.bardinpetr.itmo.lab5.network.framelevel;

import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.utils.DatagramPacketUtils;

public class ClientMain {
    static JSONSerDesService<SocketMessage> serDesService = new JSONSerDesService<>(SocketMessage.class);

    public static void main(String[] args) throws SerDesException {
        var t = new SocketMessage(
                SocketMessage.CommandType.DATA,
                Frame.FIRST_ID,
                1L,
                "As".getBytes()
        );

        byte[] msgBytes = DatagramPacketUtils.join(
                DatagramPacketUtils.separate(
                        serDesService.serialize(t)
                )
        );

        System.out.println(t.equals(serDesService.deserialize(msgBytes)));

    }
}
