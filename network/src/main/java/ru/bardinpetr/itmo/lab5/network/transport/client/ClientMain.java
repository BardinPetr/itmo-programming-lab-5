package ru.bardinpetr.itmo.lab5.network.transport.client;

import ru.bardinpetr.itmo.lab5.network.transport.models.SocketMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class ClientMain {
    public static void main(String[] args) throws IOException, TimeoutException {
        var client = new UDPClientTransport(new InetSocketAddress("localhost", 1249));
        var t = new SocketMessage(
                SocketMessage.CommandType.DATA,
                1L,
                0L,
                "A".repeat(1231231).getBytes());
        while (true) {
            client.send(t);
            var res = client.receive(Duration.ofMinutes(2));
            String payload = new String(res.getPayload());
            System.out.println(
                    res.getId() + " " +
                            res.getReplyId() + " " +
                            res.getCmdType() + " " +
                            payload.length()
//                        +" "+payload
            );
        }
    }
}
