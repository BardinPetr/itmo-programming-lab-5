package ru.bardinpetr.itmo.lab5.network.framelevel;

import ru.bardinpetr.itmo.lab5.network.utils.Pair;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrameController {
    protected final DatagramChannel channel;
    public Map<MapKey, Frame> queueMap = new HashMap<>();

    public ArrayList<MapKey> newFrameList = new ArrayList<>();

    public FrameController(DatagramChannel channel) {
        this.channel = channel;
    }

    public Pair<SocketAddress, Frame> receiveNew() throws IOException {
        if (newFrameList.size() > 0) {
            return new Pair<>(
                    newFrameList.get(0).address,
                    queueMap.remove(
                            newFrameList.remove(0)
                    )
            );
        }
        return receive(null, Frame.FIRST_ID);
    }

    public Pair<SocketAddress, Frame> receive(SocketAddress address, long id) throws IOException {
        while (true) {
            var t = singleReceive(address, id);
            if (t != null) return t;
        }
    }

    private Pair<SocketAddress, Frame> singleReceive(SocketAddress address, long id) throws IOException {
        var buffer = ByteBuffer.allocate(Frame.MAX_SIZE);

        Frame frame;
        MapKey requiredKey = new MapKey(address, id);

        if (queueMap.containsKey(requiredKey)) {
            return new Pair<>(address, queueMap.remove(requiredKey));
        }

        var clientAddress = channel.receive(buffer);
        buffer.flip();
        frame = Frame.fromBytes(buffer.array());
        if ((address == null || (address.equals(clientAddress))) && frame.getId() == id)
            return new Pair<>(clientAddress, frame);
        else {
            var mapKey = new MapKey(
                    clientAddress,
                    frame.getId()
            );
            queueMap.put(mapKey, frame);
            if (frame.getId() == Frame.FIRST_ID) {
                newFrameList.add(mapKey);
            }
            return null;
        }
    }

    public void send(SocketAddress address, Frame frame) throws IOException {
        channel.send(ByteBuffer.wrap(frame.toBytes()), address);
    }

    public record MapKey(SocketAddress address, long id) {
    }
}
