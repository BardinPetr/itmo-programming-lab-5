package ru.bardinpetr.itmo.lab5.network.client;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.server.UDPClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ClientMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        var client = new UDPClient(new InetSocketAddress("localhost", 1249));
        var t1 = new SocketMessage(SocketMessage.CommandType.DATA, 123L, 2314L, false, "A".repeat(12031023).getBytes());
        var t2 = new SocketMessage(SocketMessage.CommandType.NACK, 123L, 2314L, false, "N".getBytes());
        var t3 = new SocketMessage(SocketMessage.CommandType.ACK, 123L, 2314L, false, "D".repeat(12309123).getBytes());
        SocketMessage resp;
        Map<Integer, SocketMessage> map = new HashMap<>() {{
            put(0, t1);
            put(1, t2);
            put(2, t3);
        }};


        client.send(map.get(0));
        resp = client.receive().getSecond();
        System.out.println(resp.getReplyId() + ": " + resp.getCmdType() + " " + new String(resp.getPayload()));

    }
}
