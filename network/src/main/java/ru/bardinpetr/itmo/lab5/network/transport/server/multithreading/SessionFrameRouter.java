package ru.bardinpetr.itmo.lab5.network.transport.server.multithreading;

import ru.bardinpetr.itmo.lab5.network.transport.handlers.IMessageHandler;
import ru.bardinpetr.itmo.lab5.network.transport.interfaces.IServerTransport;
import ru.bardinpetr.itmo.lab5.network.transport.models.Frame;
import ru.bardinpetr.itmo.lab5.network.transport.models.SocketMessage;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

public class SessionFrameRouter implements IServerTransport<SocketAddress, SocketMessage> {
    private final Map<SocketAddress, Pipe> clientPipeMap = new ConcurrentHashMap<>();
    private final DatagramChannel channel;
    private IMessageHandler<SocketAddress, SocketMessage> handler;
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public SessionFrameRouter(DatagramChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(Frame.MAX_SIZE);
                SocketAddress address = channel.receive(buffer);

                if (!clientPipeMap.containsKey(address)) {
                    regClient(address);
                }

                var clientPipe = clientPipeMap.get(address);
                buffer.flip();
                clientPipe.sink().write(buffer);

            } catch (IOException ignored) {
            }
        }

    }

    private void regClient(SocketAddress address) throws IOException {
        var pipe = Pipe.open();
        clientPipeMap.put(address, pipe);
        forkJoinPool.submit(new Receiver(
                pipe,
                channel,
                address,
                handler,
                clientPipeMap
        ));
    }


    @Override
    public void send(SocketAddress recipient, SocketMessage data) {
        try {
            var pipe = Pipe.open();
            clientPipeMap.put(recipient, pipe);

            forkJoinPool.submit(new Sender(
                    pipe,
                    channel,
                    recipient,
                    data
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void subscribe(IMessageHandler<SocketAddress, SocketMessage> handler) {
        this.handler = handler;
    }


}
