package ru.bardinpetr.itmo.lab5.network.server;

import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.network.framelevel.Frame;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.server.session.Session;
import ru.bardinpetr.itmo.lab5.network.server.session.Status;
import ru.bardinpetr.itmo.lab5.network.transport.IServerTransport;
import ru.bardinpetr.itmo.lab5.network.transport.handlers.IMessageHandler;
import ru.bardinpetr.itmo.lab5.network.utils.DatagramPacketUtils;
import ru.bardinpetr.itmo.lab5.network.utils.Pair;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.*;

public class UDPSelectorServer implements IServerTransport<SocketAddress, SocketMessage> {
    private static final int TIMEOUT = 3000;
    private final Selector selector = Selector.open();
    private final List<IMessageHandler<SocketAddress, SocketMessage>> handlerList = new ArrayList<>();

    private final Queue<Pair<SocketAddress, Frame>> sendFrameQueue = new ArrayDeque<>();
    private final SelectionKey networkKey;
    private final DatagramChannel datagramChannel;
    private final Map<SocketAddress, Pipe.SinkChannel> clientSinkMap = new HashMap<>();
    JSONSerDesService<SocketMessage> serDesService = new JSONSerDesService<>(SocketMessage.class);


    public UDPSelectorServer(DatagramChannel channel) throws IOException {
        datagramChannel = channel;
        networkKey = channel.register(selector, SelectionKey.OP_READ, Session.getNetworkSession());

    }

    public void run() throws IOException {

        System.out.println("listening..");

        SocketAddress clientAdr;

        while (true) {
            // Wait for task or until timeout expires
            if (selector.select(TIMEOUT) == 0) {
//                System.out.print(".");
                continue;
            }
            // Get iterator on set of keys with I/O to process
            var keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next(); // Key is bit mask
                keyIter.remove();

                // Client socket channel has pending data?
                if (key.isReadable()) {
                    if (key.equals(networkKey)) { //Data channel message
                        DatagramChannel channel = (DatagramChannel) key.channel();

                        ByteBuffer buffer = ByteBuffer.allocate(Frame.MAX_SIZE);
                        SocketAddress address = channel.receive(buffer);

                        Frame frame = Frame.fromBytes(buffer.array());

                        if (frame.getId() == Frame.FIRST_ID) {
                            regRecvClient(address, frame);
                        } else {
                            if (clientSinkMap.containsKey(address)) {
                                var sink = clientSinkMap.get(address);
                                sink.write(ByteBuffer.wrap(frame.toBytes()));
                            }
                        }

                    } else { //internal message
                        Session session = (Session) key.attachment();

                        Frame frame = Frame.fromChannel((Pipe.SourceChannel) key.channel());

                        if (session.getStatus().equals(Status.IDLE)) {
                            headerFrame(session, frame);
                        } else if (session.getStatus().equals(Status.READING)) {
                            readNewFrame(session, frame);
                        } else if (session.getStatus().equals(Status.SENDING)) {
                            if (session.getSendFrameList().size() > 0) {
                                sendFrameQueue.add(
                                        new Pair<>(
                                                session.getConsumerAddress(),
                                                session.getSendFrameList().remove(0)
                                        )
                                );
                            } else {
                                session.setStatus(Status.HALT);
                                //TODO close session
                            }
                        }

                    }
                }

            }

            for (int i = 0; i < sendFrameQueue.size(); i++) {
                var pair = sendFrameQueue.remove();
                datagramChannel.send(
                        ByteBuffer.wrap(pair.getSecond().toBytes()),
                        pair.getFirst());
            }

        }
    }

    private void readNewFrame(Session session, Frame frame) {
        session.addToList(frame);
        sendFrameQueue.add(
                new Pair<>(
                        session.getConsumerAddress(),
                        new Frame(frame.getId())
                )
        );

        if (session.checkFinishReading()) {

            session.setStatus(Status.READINGFINISHED);

            var desBytes = DatagramPacketUtils.joinFromFrames(List.of(session.getReceiveFrameList()));
            SocketMessage msg;
            try {
                msg = serDesService.deserialize(desBytes);
            } catch (SerDesException e) {
                msg = new SocketMessage(new byte[]{});
            }//TODO execution
            for (var handler : handlerList) {
                handler.handle(
                        session.getConsumerAddress(),
                        msg
                );
            }
//            System.out.println("%s %s %s %s".formatted(msg.getId(), msg.getCmdType(), msg.getReplyId(), new String(msg.getPayload()).equals("A".repeat(121))));
        }

    }


    private void headerFrame(Session session, Frame frame) {
        int framesCount = DatagramPacketUtils.BytesToInt(
                ByteBuffer.wrap(frame.getPayload())
        );
        sendFrameQueue.add(
                new Pair<>(
                        session.getConsumerAddress(),
                        new Frame(Frame.FIRST_ID + 1)
                )
        );
        session.setReceiveFrameList(new Frame[framesCount]);
        session.setStatus(Status.READING);
    }

    private void regRecvClient(SocketAddress address, Frame frame) throws IOException {
        regClient(address, frame, Status.IDLE, null);
    }

    private void regSendClient(SocketAddress address, Frame frame, List<Frame> frameList) throws IOException {
        regClient(address, frame, Status.SENDING, frameList);
    }

    private void regClient(SocketAddress address, Frame frame, Status status, List<Frame> sendList) throws IOException {
        var pipe = Pipe.open();

        var sink = pipe.sink();
        var source = pipe.source();

        source.configureBlocking(false);
        source.register(
                selector,
                SelectionKey.OP_READ,
                new Session(
                        address,
                        status,
                        pipe,
                        sendList
                ));
        clientSinkMap.put(
                address,
                sink
        );
        sink.write(ByteBuffer.wrap(frame.toBytes()));

    }


    @Override
    public void send(SocketAddress recipient, SocketMessage data) {
        List<Frame> frameList = null;

        try {
            frameList = DatagramPacketUtils.separateToFrames(
                    serDesService.serialize(data)
            );
        } catch (SerDesException ignored) {
        }

        try {
            regSendClient(
                    recipient,
                    new Frame(
                            Frame.FIRST_ID,
                            DatagramPacketUtils.IntToBytes(frameList.size()).array()
                    ),
                    frameList
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sendFrameQueue.add(
                new Pair<>(
                        recipient,
                        new Frame(
                                Frame.FIRST_ID,
                                DatagramPacketUtils.IntToBytes(frameList.size()).array()
                        )
                )
        );
    }

    @Override
    public void subscribe(IMessageHandler<SocketAddress, SocketMessage> handler) {
        handlerList.add(handler);
    }
}
