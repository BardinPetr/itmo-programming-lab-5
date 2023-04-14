package ru.bardinpetr.itmo.lab5.network.transport.client;

import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.network.transport.errors.TransportException;
import ru.bardinpetr.itmo.lab5.network.transport.errors.TransportTimeoutException;
import ru.bardinpetr.itmo.lab5.network.transport.interfaces.IClientTransport;
import ru.bardinpetr.itmo.lab5.network.transport.models.Frame;
import ru.bardinpetr.itmo.lab5.network.transport.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.utils.TransportUtils;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for sending and receiving socket message  any length via UPD channel
 */
public class UDPClientTransport implements IClientTransport<SocketMessage> {
    private final DatagramSocket socket;
    private final Duration sendDurationTimeout = Duration.ofMinutes(2); // TODO
    private final SocketAddress serverAddress;
    JSONSerDesService<SocketMessage> serDesService = new JSONSerDesService<>(SocketMessage.class);

    /**
     * @param socketAddress server address
     */
    public UDPClientTransport(SocketAddress socketAddress) {
        DatagramSocket tmp = null;
        try {
            tmp = new DatagramSocket(null);
            tmp.setReuseAddress(true);
        } catch (IOException ignore) {
        }

        socket = tmp;
        serverAddress = socketAddress;
    }

    /**
     * Inner function for receiving throw UDP channel
     *
     * @param duration timeout duration
     * @return received frame
     * @throws TransportException exception via receiving
     */
    private Frame receiveFrame(Duration duration) throws TransportException, TransportTimeoutException {
        var buffer = new byte[Frame.MAX_SIZE];
        var packet = new DatagramPacket(buffer, buffer.length);
        try {
            socket.setSoTimeout((int) duration.getSeconds() * 1000);
            socket.receive(packet);
        } catch (SocketTimeoutException timeoutException) {
            throw new TransportTimeoutException();
        } catch (IOException connectExc) {
            throw new TransportException();
        }
        return Frame.fromBytes(packet.getData());
    }

    private void connect() throws TransportException {
        try {
            socket.connect(serverAddress);
        } catch (SocketException socketException) {
            throw new TransportException();
        }
    }

    private void disconnect() {
        socket.disconnect();
    }

    private void sendFrame(Frame frame) throws TransportException {
        try {
            socket.send(new DatagramPacket(frame.toBytes(), frame.toBytes().length, serverAddress));
        } catch (IOException e) {
            throw new TransportException(e);
        }
    }

    /**
     * Method for sending socket message any length.
     *
     * @param msg message to be send
     */
    @Override
    public void send(SocketMessage msg) throws TransportException, TransportTimeoutException {
        connect();

        byte[] msgBytes;
        try {
            msgBytes = serDesService.serialize(msg);
        } catch (SerDesException e) {
            throw new TransportException("Could not serialize message");
        }

        var packetsList = TransportUtils.separateBytes(msgBytes);

        var buffer = TransportUtils.IntToBytes(packetsList.size());

        var header = new Frame(
                Frame.FIRST_ID,
                buffer.array());

        sendFrame(header);
        header.checkACK(receiveFrame(sendDurationTimeout));

        for (Frame tmpFrame : packetsList) {
            sendFrame(tmpFrame);
            tmpFrame.checkACK(receiveFrame(sendDurationTimeout));
        }
        disconnect();
    }

    /**
     * Receive socket message from server
     *
     * @param duration timeout or null if no timeout should be applied
     * @return received socket message
     */
    public SocketMessage receive(Duration duration) throws TransportException, TransportTimeoutException {
        // seg 1 end -> create session
        Frame header = receiveFrame(duration);

        int size = ByteBuffer.wrap(header.getPayload()).getInt();

        sendFrame(new Frame(Frame.FIRST_ID));

        // seg 2 end -> wait n packets + set user lock
        List<Frame> frameList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            frameList.add(receiveFrame(duration));
            sendFrame(new Frame(Frame.FIRST_ID + 2 + i));
        }

        SocketMessage msg;
        try {
            msg = serDesService.deserialize(TransportUtils.joinFrames(frameList));
        } catch (SerDesException e) {
            throw new TransportException("Could not serialize message");
        }

        return msg;
    }
}
