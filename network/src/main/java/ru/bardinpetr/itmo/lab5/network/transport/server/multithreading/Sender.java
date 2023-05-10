package ru.bardinpetr.itmo.lab5.network.transport.server.multithreading;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.network.transport.errors.TransportException;
import ru.bardinpetr.itmo.lab5.network.transport.models.Frame;
import ru.bardinpetr.itmo.lab5.network.transport.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.utils.TransportUtils;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.util.concurrent.RecursiveAction;

@Slf4j
public class Sender extends RecursiveAction {
    private final DatagramChannel channel;
    private final SocketAddress address;
    private final SocketMessage message;
    private final Pipe pipe;
    JSONSerDesService<SocketMessage> serDesService = new JSONSerDesService<>(SocketMessage.class);

    public Sender(Pipe pipe, DatagramChannel channel, SocketAddress address, SocketMessage message) {
        this.pipe = pipe;
        this.channel = channel;
        this.address = address;
        this.message = message;
    }

    @Override
    protected void compute() {  //компот
        try {

            var frameList = TransportUtils.separateBytes(
                    serDesService.serialize(message)
            );
            var lenInBytes = TransportUtils.IntToBytes(frameList.size());
            channel.send(
                    packBytesToFrame(
                            0,
                            lenInBytes.array()
                    ), address);

            receiveAndCheck(0);

            for (int i = 0; i < frameList.size(); i++) {
                channel.send(ByteBuffer.wrap(frameList.get(i).toBytes()), address);
                receiveAndCheck(i + 2);
            }
            log.info("Send message to " + address);

        } catch (SerDesException ignored) {
        } catch (IOException e) {
            log.info("Sending frame exception");
            throw new TransportException(e);
        }
    }

    private void receiveAndCheck(int id) throws IOException {
        var frame = Frame.fromChannel(pipe.source());
        if (frame.getId() != id) {
            log.error("Expected id %d, but current is %d ", id, frame.getId());
        }

    }

    private ByteBuffer packBytesToFrame(int id, byte[] bytes) {
        return ByteBuffer.wrap(new Frame(
                        id,
                        bytes
                ).toBytes()
        );
    }
}
