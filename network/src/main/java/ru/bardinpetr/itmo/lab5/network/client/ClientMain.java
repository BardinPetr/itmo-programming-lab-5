package ru.bardinpetr.itmo.lab5.network.client;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.server.UDPClient;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ClientMain {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        var client = new UDPClient(new InetSocketAddress("localhost", 1249));
        var t1 = new SocketMessage(SocketMessage.CommandType.DATA, 123L, 2314L, "Artem the best".getBytes());

        client.send(t1);

        var msg = client.receive(null);
        var t = new String(msg.getPayload());
        System.out.println(msg.getCmdType() + " " + t + " " + t.length());

//        client.send(t1);
//        var selector = Selector.open();
//        var p = Pipe.open();
//
//        var sink = p.sink();
//        var source = p.source();
//
//        source.configureBlocking(false);
//        source.register(selector, SelectionKey.OP_READ);
//
//
//        sink.write(ByteBuffer.wrap(new byte[]{1, 2, 3}));
//
//        while (true) {
//            if (selector.select(1L) == 0) continue;
//
//            var it = selector.selectedKeys().iterator();
//            while (it.hasNext()) {
//                var k = it.next();
//                it.remove();
//
//                var ch = (Pipe.SourceChannel) k.channel();
//            }
//        }
    }
}
