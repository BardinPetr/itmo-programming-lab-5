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

public class UPDConnector implements IServerTransport<SocketAddress, SocketMessage> {
    private final Map<SocketAddress, Pipe> clientPipeMap = new ConcurrentHashMap<>();
    private final DatagramChannel channel;
    private IMessageHandler<SocketAddress, SocketMessage> handler;

    public UPDConnector(DatagramChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        var forkJoinPool = new ForkJoinPool(); //count of threads
        while (true) {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(Frame.MAX_SIZE);
                SocketAddress address = channel.receive(buffer);
                if (!clientPipeMap.containsKey(address)) {
                    var pipe = Pipe.open();
                    clientPipeMap.put(address, pipe);
                    forkJoinPool.invoke(new Receiver(pipe));
                }

                var clientPipe = clientPipeMap.get(address);
                clientPipe.sink().write(buffer);

            } catch (IOException ignored) {
            }
        }

    }

    private void regClient(SocketAddress address, ByteBuffer buffer) throws IOException {
        var pipe = Pipe.open();

        var sink = pipe.sink();
        var source = pipe.source();

        source.configureBlocking(false);
        clientPipeMap.put(
                address,
                pipe
        );
        sink.write(buffer);
    }

    @Override
    public void send(SocketAddress recipient, SocketMessage data) {

    }

    @Override
    public void subscribe(IMessageHandler<SocketAddress, SocketMessage> handler) {
        this.handler = handler;
    }


}
