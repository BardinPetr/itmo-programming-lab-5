package ru.bardinpetr.itmo.lab5.network.general;

import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.network.framelevel.Frame;
import ru.bardinpetr.itmo.lab5.network.framelevel.FrameController;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.utils.DatagramPacketUtils;
import ru.bardinpetr.itmo.lab5.network.utils.Pair;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;

public class UDPServerController {
    FrameController frameController;

    JSONSerDesService<SocketMessage> serDesService = new JSONSerDesService<>(SocketMessage.class);


    public UDPServerController(DatagramChannel datagramChannel) {
        this.frameController = new FrameController(datagramChannel);
    }

    public Pair<SocketAddress, SocketMessage> receive() {
        try {
            SocketAddress clientAdr;

            var headerPair = frameController.receiveNew();

            clientAdr = headerPair.getFirst();

            Frame header = headerPair.getSecond();

            int size = DatagramPacketUtils.BytesToInt(ByteBuffer.wrap(header.getPayload()));

            frameController.send(clientAdr, new Frame(Frame.FIRST_ID + 1L));

            List<byte[]> bytesList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                bytesList.add(frameController.receive(
                        clientAdr,
                        Frame.FIRST_ID + 2 + i * 2L
                ).getSecond().getPayload());
                frameController.send(clientAdr, new Frame(Frame.FIRST_ID + 3 + i * 2L));
            }

            SocketMessage msg;
            msg = serDesService.deserialize(DatagramPacketUtils.join(bytesList));

            return new Pair<>(clientAdr, msg);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SerDesException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(SocketAddress address, SocketMessage msg) {
        byte[] msgBytes;

        try {
            msgBytes = serDesService.serialize(msg);
        } catch (SerDesException e) {
            throw new RuntimeException(e);
        }

        var packetsList = DatagramPacketUtils.separate(msgBytes);

        var buffer = DatagramPacketUtils.IntToBytes(packetsList.size());

        try {
            frameController.send(address,
                    new Frame(Frame.FIRST_ID,
                            buffer.array()));
            frameController.receive(address, Frame.FIRST_ID + 1);

            for (int i = 0; i < packetsList.size(); i++) {
                frameController.send(address,
                        new Frame(
                                Frame.FIRST_ID + 2 + i * 2L,
                                packetsList.get(i)
                        )
                );
                frameController.receive(address, Frame.FIRST_ID + 2 + 1 + i * 2L);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}