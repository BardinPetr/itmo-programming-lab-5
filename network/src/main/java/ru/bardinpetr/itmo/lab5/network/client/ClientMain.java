package ru.bardinpetr.itmo.lab5.network.client;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.server.UDPClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Duration;

public class ClientMain {
    private static final UDPClient client = new UDPClient(new InetSocketAddress("localhost", 1249));

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        var t1 = new SocketMessage(SocketMessage.CommandType.DATA, 123L, 2314L, "Artem the best".getBytes());


        for (int i = 0; i < 100; i++) {
            var thr = new Thread(() -> clientRun(t1));
            thr.run();
        }

    }

    public static void clientRun(SocketMessage t1) {
        SocketMessage msg;
        String t;
        while (true) {
            client.send(t1);
            msg = client.receive(Duration.ofSeconds(2));
            t = new String(msg.getPayload());
            System.out.println(msg.getCmdType() + " " + t + " " + t.length());
        }
    }


}
