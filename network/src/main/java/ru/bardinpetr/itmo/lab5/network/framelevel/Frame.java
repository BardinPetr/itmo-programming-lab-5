package ru.bardinpetr.itmo.lab5.network.framelevel;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.network.server.errors.SizeLimitException;

import java.io.IOException;
import java.nio.ByteBuffer;

@Data
public class Frame {
    public static int HEADER_SIZE = Long.SIZE / 8;
    public static int PAYLOAD_SIZE = 1016;
    public static int MAX_SIZE = HEADER_SIZE + PAYLOAD_SIZE;

    public static long FIRST_ID = 0;

    private final long id;
    private final byte[] payload;

    public Frame(long id, byte[] data) {
        if (data.length > (MAX_SIZE)) throw new SizeLimitException("Frame limit");
        this.id = id;
        this.payload = data;
    }

    public Frame(long id) {
        this(id, new byte[0]);
    }

    public static Frame fromBytes(byte[] bytes) {
        var byteChannel = ByteBuffer.wrap(bytes);
        long id = byteChannel.getLong();

        byte[] payload = new byte[byteChannel.remaining()];
        byteChannel.get(payload);

        return new Frame(id, payload);
    }

    public byte[] toBytes() throws IOException {
        var byteChannel = ByteBuffer.allocate(HEADER_SIZE + getPayload().length);
        byteChannel.putLong(getId());
        byteChannel.put(getPayload());
        return byteChannel.array();
    }

}
