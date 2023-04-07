package ru.bardinpetr.itmo.lab5.network.framelevel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class FrameServer extends FrameController {
    public FrameServer(DatagramChannel channel) {
        super(channel);
    }

    public void run() throws IOException {
        while (true) {
            var buffer = ByteBuffer.allocate(Frame.MAX_SIZE);
            super.channel.receive(buffer);

        }
    }
}
