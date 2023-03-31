package ru.bardinpetr.itmo.lab5.network.general;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.serdes.SocketMessageSerDes;
import ru.bardinpetr.itmo.lab5.network.server.errors.ReceiveException;
import ru.bardinpetr.itmo.lab5.network.server.errors.SendException;
import ru.bardinpetr.itmo.lab5.network.utils.Pair;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPServerController implements IServerController {
    DatagramChannel datagramChannel;

    public UDPServerController(DatagramChannel datagramChannel) {
        this.datagramChannel = datagramChannel;
    }

    public Pair<SocketAddress, SocketMessage> receive() {
        var buffer = ByteBuffer.allocate(1024);
        SocketAddress clientAdr = null;
        try {
            clientAdr = datagramChannel.receive(buffer);
        } catch (Exception e) {
            throw new ReceiveException(e);
        }
        var obj = SocketMessageSerDes.deserialize(buffer.array());
        return new Pair<>(clientAdr, obj);
    }

    public void send(SocketAddress address, SocketMessage msg) {
        byte[] bytes = SocketMessageSerDes.serialize(msg);

        try {
            datagramChannel.send(ByteBuffer.wrap(bytes), address);
        } catch (Exception e) {
            throw new SendException(e);
        }
    }


}
