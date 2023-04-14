package ru.bardinpetr.itmo.lab5.network.transport.models;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.network.transport.errors.SizeLimitException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

@Data
public class Frame {
    public static int HEADER_SIZE = (Long.SIZE + Integer.SIZE) / 8;
    public static int PAYLOAD_SIZE = 1024 - HEADER_SIZE;
    public static int MAX_SIZE = HEADER_SIZE + PAYLOAD_SIZE;

    public static long FIRST_ID = 0;

    private final long id;
    private final int currentPayloadSize;
    private final byte[] payload;

    public Frame(long id, byte[] data) {
        if (data.length > (PAYLOAD_SIZE)) throw new SizeLimitException("Frame limit");
        this.id = id;
        this.payload = data;
        currentPayloadSize = data.length;
    }

    public Frame(long id) {
        this(id, new byte[0]);
    }

    public static Frame fromChannel(ReadableByteChannel channel) throws IOException {
        var buffer = ByteBuffer.allocate(Frame.MAX_SIZE);
        channel.read(buffer);
        return Frame.fromBytes(buffer.array());
    }

    public static Frame fromBytes(byte[] bytes) {
        var byteChannel = ByteBuffer.wrap(bytes);
        int payloadSize = byteChannel.getInt();
        long id = byteChannel.getLong();
        byte[] payload = new byte[payloadSize];
        byteChannel.get(payload);

        return new Frame(id, payload);
    }

    public boolean checkACK(Frame frame) {
        if (frame.id == id) {
            return true;
        } else {
            throw new RuntimeException();
        }
    }

    public byte[] toBytes() {
        var byteChannel = ByteBuffer.allocate(HEADER_SIZE + getPayload().length);
        byteChannel.putInt(getCurrentPayloadSize());
        byteChannel.putLong(getId());
        byteChannel.put(getPayload());
        return byteChannel.array();
    }

}
