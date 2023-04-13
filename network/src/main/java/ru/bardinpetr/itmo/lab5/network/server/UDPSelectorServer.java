package ru.bardinpetr.itmo.lab5.network.server;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.network.framelevel.Frame;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.server.session.Session;
import ru.bardinpetr.itmo.lab5.network.server.session.Status;
import ru.bardinpetr.itmo.lab5.network.transport.IServerTransport;
import ru.bardinpetr.itmo.lab5.network.transport.handlers.IMessageHandler;
import ru.bardinpetr.itmo.lab5.network.utils.Pair;
import ru.bardinpetr.itmo.lab5.network.utils.TransportUtils;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.*;

/**
 * Server class for receiving and sending messages from clients
 */
@Slf4j
public class UDPSelectorServer implements IServerTransport<SocketAddress, SocketMessage> {
    private static final int TIMEOUT = 3000;
    private final Selector selector = Selector.open();
    private IMessageHandler<SocketAddress, SocketMessage> handler = null;
    private final Queue<Pair<SocketAddress, Frame>> sendFrameQueue = new ArrayDeque<>();
    private final SelectionKey networkKey;
    private final DatagramChannel datagramChannel;
    private final Map<SocketAddress, Pipe.SinkChannel> clientSinkMap = new HashMap<>();
    JSONSerDesService<SocketMessage> serDesService = new JSONSerDesService<>(SocketMessage.class);


    public UDPSelectorServer(DatagramChannel channel) throws IOException {
        datagramChannel = channel;
        networkKey = channel.register(selector, SelectionKey.OP_READ, Session.getNetworkSession());

    }

    /**
     * Main loop for receiving client from server and executing
     *
     * @throws IOException
     */
    public void run() throws IOException {

        System.out.println("listening..");

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
                            readNewFrame(key, frame);
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
                                closeSessionByKey(key);
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

    /**
     * Adding frame to receiving list, adding ACK to sending list, check if finished
     *
     * @param key   Client key
     * @param frame
     */
    private void readNewFrame(SelectionKey key, Frame frame) {
        Session session = (Session) key.attachment();
        session.addToList(frame);
        sendFrameQueue.add(
                new Pair<>(
                        session.getConsumerAddress(),
                        new Frame(frame.getId())
                )
        );

        if (session.checkFinishReading()) {
            finishReading(key);
        }
    }

    /**
     * Serialize socket message form received byte list
     *
     * @param key client key
     */
    private void finishReading(SelectionKey key) {
        Session session = (Session) key.attachment();
        session.setStatus(Status.READINGFINISHED);

        var desBytes = TransportUtils.joinFrames(List.of(session.getReceiveFrameList()));
        SocketMessage msg;
        try {
            msg = serDesService.deserialize(desBytes);
        } catch (SerDesException e) {
            msg = new SocketMessage(new byte[]{});
        }
        handler.handle(
                session.getConsumerAddress(),
                msg
        );
        closeSessionByKey(key);

    }

    /**
     * Receive header frame with frame count
     *
     * @param session currnet user session
     * @param frame   received header frame
     */
    private void headerFrame(Session session, Frame frame) {
        int framesCount = ByteBuffer.wrap(frame.getPayload()).getInt();
        sendFrameQueue.add(
                new Pair<>(
                        session.getConsumerAddress(),
                        new Frame(Frame.FIRST_ID + 1)
                )
        );
        session.setReceiveFrameList(new Frame[framesCount]);
        session.setStatus(Status.READING);
    }

    /**
     * Registration new clint session with IDLE status
     *
     * @param address
     * @param frame
     * @throws IOException
     */
    private void regRecvClient(SocketAddress address, Frame frame) throws IOException {
        regClient(address, frame, Status.IDLE, null);
    }

    /**
     * Registration new client session with SENDING status
     *
     * @param address   client address
     * @param frame     header frame
     * @param frameList list for sending
     * @throws IOException
     */
    private void regSendClient(SocketAddress address, Frame frame, List<Frame> frameList) throws IOException {
        regClient(address, frame, Status.SENDING, frameList);
    }

    /**
     * @param address  client address
     * @param frame    header frame
     * @param status   status
     * @param sendList frame list for sending
     * @throws IOException
     */
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

    /**
     * Close sink and source channels, remove client from clientSinkMap and close this selector channel by key
     *
     * @param key selector key to be closed
     */
    private void closeSessionByKey(SelectionKey key) {
        var session = (Session) key.attachment();
        try {
            var pipe = session.getPipe();
            pipe.sink().close();
            pipe.source().close();
            clientSinkMap.remove(session.getConsumerAddress());
            key.cancel();
        } catch (IOException ignored) {
        }
        session = null;
    }

    /**
     * Register client session with frame list for sending and put it to the sending queue
     *
     * @param recipient
     * @param data
     */
    @Override
    public void send(SocketAddress recipient, SocketMessage data) {
        List<Frame> frameList = null;

        try {
            frameList = TransportUtils.separateBytes(
                    serDesService.serialize(data)
            );
        } catch (SerDesException ignored) {
        }

        try {
            regSendClient(
                    recipient,
                    new Frame(
                            Frame.FIRST_ID,
                            TransportUtils.IntToBytes(frameList.size()).array()
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
                                TransportUtils.IntToBytes(frameList.size()).array()
                        )
                )
        );
    }

    /**
     * Register handler for transporting socket message
     *
     * @param handler message handler
     */
    @Override
    public void subscribe(IMessageHandler<SocketAddress, SocketMessage> handler) {
        this.handler = handler;
    }
}
