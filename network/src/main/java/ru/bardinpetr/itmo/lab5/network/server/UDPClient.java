package ru.bardinpetr.itmo.lab5.network.server;

import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.network.framelevel.Frame;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.transport.IClientTransport;
import ru.bardinpetr.itmo.lab5.network.utils.DatagramPacketUtils;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UDPClient implements IClientTransport<SocketMessage> {
    private final SocketAddress serverAddress;
    private final DatagramChannel channel;
    //    private final Selector selector;
    private final Duration sendDurationTimeout = Duration.ofSeconds(5);
    JSONSerDesService<SocketMessage> serDesService = new JSONSerDesService<>(SocketMessage.class);


    public UDPClient(SocketAddress socketAddress) throws IOException {
        channel = DatagramChannel.open().bind(null);
//        channel.configureBlocking(false);

//        selector = Selector.open();
//        channel.register(selector, SelectionKey.OP_READ);

        this.serverAddress = socketAddress;
    }

    private Frame receiveFrame(Duration duration) throws IOException {
        var start = System.nanoTime();
        var buffer = ByteBuffer.allocate(Frame.MAX_SIZE);
        channel.receive(buffer);
        return Frame.fromBytes(buffer.array());
//        while (true){
//            var keyIter = selector.selectedKeys().iterator();
//            while (keyIter.hasNext()) {
//                SelectionKey key = keyIter.next(); // Key is bit mask
//                keyIter.remove();
//
//                // Client socket channel has pending data?
//                if (key.isReadable()) {
//                    DatagramChannel datagramChannel = (DatagramChannel) key.channel();
//                    buffer = ByteBuffer.allocate(Frame.MAX_SIZE);
//                    datagramChannel.receive(buffer);
//                    return Frame.fromBytes(buffer.array());
//                }
//            }
//        }

    }

    public void send(SocketMessage msg) {
        byte[] msgBytes;
        try {
            msgBytes = serDesService.serialize(msg);
        } catch (SerDesException e) {
            throw new RuntimeException(e);
        }

        var packetsList = DatagramPacketUtils.separateToFrames(msgBytes);

        var buffer = DatagramPacketUtils.IntToBytes(packetsList.size());

        try {
            channel.send(ByteBuffer.wrap(new Frame(
                            Frame.FIRST_ID,
                            buffer.array()).toBytes()),
                    serverAddress);
            receiveFrame(sendDurationTimeout);

            for (int i = 0; i < packetsList.size(); i++) {
                channel.send(ByteBuffer.wrap(packetsList.get(i).toBytes()),
                        serverAddress
                );
                receiveFrame(sendDurationTimeout);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SocketMessage receive(Duration duration) {
        try {
            // seg 1 end -> create session
            Frame header = receiveFrame(duration);

            int size = DatagramPacketUtils.BytesToInt(ByteBuffer.wrap(header.getPayload()));

            channel.send(ByteBuffer.wrap(new Frame(Frame.FIRST_ID + 1L).toBytes()),
                    serverAddress);
            // seg 2 end -> wait n packets + set user lock
            List<Frame> frameList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                frameList.add(receiveFrame(duration));
                channel.send(
                        ByteBuffer.wrap(new Frame(Frame.FIRST_ID + 2 + i).toBytes()),
                        serverAddress);
            }

            SocketMessage msg;
            msg = serDesService.deserialize(DatagramPacketUtils.joinFromFrames(frameList));

            return msg;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SerDesException e) {
            throw new RuntimeException(e);
        }

    }


}
