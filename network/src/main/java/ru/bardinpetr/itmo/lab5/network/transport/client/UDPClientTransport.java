package ru.bardinpetr.itmo.lab5.network.transport.client;

import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.network.transport.interfaces.IClientTransport;
import ru.bardinpetr.itmo.lab5.network.transport.models.Frame;
import ru.bardinpetr.itmo.lab5.network.transport.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.utils.TransportUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for sending and receiving socket message  any length via UPD channel
 */
public class UDPClientTransport implements IClientTransport<SocketMessage> {
    private final DatagramSocket socket;
    private final Duration sendDurationTimeout = Duration.ofSeconds(5);
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
            tmp.bind(socketAddress);
        } catch (IOException ignore) {
        }
        socket = tmp;
        this.serverAddress = socketAddress;
    }

    /**
     * Inner function for receiving throw UDP channel
     *
     * @param duration timeout duration
     * @return received frame
     * @throws IOException exception via receiving
     */
    private Frame receiveFrame(Duration duration) throws IOException {
        socket.setSoTimeout((int) duration.getSeconds() * 1000);
        var buffer = new byte[Frame.MAX_SIZE];
        var packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return Frame.fromBytes(packet.getData());
    }

    private void sendFrame(Frame frame) throws IOException {
        socket.send(new DatagramPacket(frame.toBytes(), frame.toBytes().length, serverAddress));
    }

    /**
     * Method for sending socket message any length.
     *
     * @param msg message to be send
     */
    public void send(SocketMessage msg) {
        byte[] msgBytes;
        try {
            msgBytes = serDesService.serialize(msg);
        } catch (SerDesException e) {
            throw new RuntimeException(e);
        }

        var packetsList = TransportUtils.separateBytes(msgBytes);

        var buffer = TransportUtils.IntToBytes(packetsList.size());

        try {
            sendFrame(new Frame(
                    Frame.FIRST_ID,
                    buffer.array()));
            receiveFrame(sendDurationTimeout);

            for (int i = 0; i < packetsList.size(); i++) {
                sendFrame(packetsList.get(i));
                receiveFrame(sendDurationTimeout);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Receive socket message from server
     *
     * @param duration timeout or null if no timeout should be applied
     * @return received socket message
     */
    public SocketMessage receive(Duration duration) {
        try {
            // seg 1 end -> create session
            Frame header = receiveFrame(duration);

            int size = ByteBuffer.wrap(header.getPayload()).getInt();

            sendFrame(new Frame(Frame.FIRST_ID + 1L));

            // seg 2 end -> wait n packets + set user lock
            List<Frame> frameList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                frameList.add(receiveFrame(duration));
                sendFrame(new Frame(Frame.FIRST_ID + 2 + i));
            }

            SocketMessage msg;
            msg = serDesService.deserialize(TransportUtils.joinFrames(frameList));

            return msg;

        } catch (IOException | SerDesException e) {
            throw new RuntimeException(e);
        }
    }
}
