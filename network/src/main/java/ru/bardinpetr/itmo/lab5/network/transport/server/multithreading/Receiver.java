package ru.bardinpetr.itmo.lab5.network.transport.server.multithreading;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.network.transport.errors.TransportException;
import ru.bardinpetr.itmo.lab5.network.transport.handlers.IMessageHandler;
import ru.bardinpetr.itmo.lab5.network.transport.models.Frame;
import ru.bardinpetr.itmo.lab5.network.transport.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.utils.TransportUtils;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.RecursiveAction;

@Slf4j
public class Receiver extends RecursiveAction {
    private final Pipe pipe;
    private final Pipe.SourceChannel pipeSource;
    private final DatagramChannel channel;
    private final SocketAddress address;
    private final IMessageHandler<SocketAddress, SocketMessage> handler;
    private final Map<SocketAddress, Pipe> clientsMap;
    JSONSerDesService<SocketMessage> serDesService = new JSONSerDesService<>(SocketMessage.class);


    public Receiver(Pipe pipe, DatagramChannel channel, SocketAddress address, IMessageHandler<SocketAddress, SocketMessage> handler, Map<SocketAddress, Pipe> map) {
        this.handler = handler;
        this.pipe = pipe;
        this.address = address;
        this.channel = channel;
        this.pipeSource = pipe.source();
        clientsMap = map;
    }

    private void respondToFrame(int frameId) throws IOException {
        channel.send(
                ByteBuffer.wrap(
                        new Frame(frameId).toBytes()
                ),
                address);
    }

    @Override
    protected void compute() {
        try {
            var initFrame = Frame.fromChannel(pipeSource);
            respondToFrame(0);
            int len = ByteBuffer.wrap(initFrame.getPayload()).getInt();

            log.info("Got new socket message with frames list size: " + len);
            ArrayList<Frame> receiveList = new ArrayList<>(len);

            for (int i = 0; i < len; i++) {
                receiveList.add(
                        Frame.fromChannel(pipeSource)
                );
                respondToFrame(i + 2);
            }

            var desBytes = TransportUtils.joinFrames(receiveList);
            SocketMessage msg;
            try {
                msg = serDesService.deserialize(desBytes);      //dived to threads
            } catch (SerDesException e) {
                msg = new SocketMessage(new byte[]{});
            }
            log.info("Deserialized frames to socket message");

            handler.handle(address, msg);
        } catch (IOException e) {
            log.error("Error during receiving session", e);
            throw new TransportException(e);
        } finally {
            try {
                closeSession();
            } catch (IOException e) {
                log.error("Error during closing receiving session", e);
                throw new TransportException(e);
            }
        }
    }

    private void closeSession() throws IOException {
        clientsMap.remove(address);
        pipe.sink().close();
        pipe.source().close();
    }
}
