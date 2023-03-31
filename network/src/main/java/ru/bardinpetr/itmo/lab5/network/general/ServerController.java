package ru.bardinpetr.itmo.lab5.network.general;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.serdes.SocketMessageSerDes;
import ru.bardinpetr.itmo.lab5.network.utils.Pair;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerController implements IServerController {
    DatagramChannel datagramChannel;

    public ServerController(DatagramChannel datagramChannel) {
        this.datagramChannel = datagramChannel;
    }

    public Pair<SocketAddress, SocketMessage> receive() throws IOException {
        var buffer = ByteBuffer.allocate(1024);
        SocketAddress clientAdr = datagramChannel.receive(buffer);
        var obj = SocketMessageSerDes.deserialize(buffer.array());
        return new Pair<>(clientAdr, obj);
    }

    public void send(SocketMessage msg, SocketAddress address) throws IOException {
        byte[] bytes = SocketMessageSerDes.serialize(msg);

        datagramChannel.send(ByteBuffer.wrap(bytes), address);
    }


}
