package ru.bardinpetr.itmo.lab5.network.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class ClientMain {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        var selector = Selector.open();
        var p = Pipe.open();

        var sink = p.sink();
        var source = p.source();

        source.configureBlocking(false);
        source.register(selector, SelectionKey.OP_READ);

//        var str = Channels.newOutputStream(sink);
//        var oos = new ObjectOutputStream(str);
//        oos.writeObject("asdfgh");

        sink.write(ByteBuffer.wrap(new byte[]{1, 2, 3}));

        while (true) {
            if (selector.select(1L) == 0) continue;

            var it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                var k = it.next();
                it.remove();

                var ch = (Pipe.SourceChannel) k.channel();
            }
        }
    }
}
