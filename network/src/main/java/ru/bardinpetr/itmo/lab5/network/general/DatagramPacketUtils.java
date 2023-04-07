package ru.bardinpetr.itmo.lab5.network.general;

import ru.bardinpetr.itmo.lab5.network.framelevel.Frame;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatagramPacketUtils {

    public static ByteBuffer IntToBytes(int i) {
        var buffer = ByteBuffer.allocate(4);
        return buffer.putInt(i);
    }

    public static int BytesToInt(ByteBuffer buffer) {
        return buffer.getInt();
    }

    public static byte[] join(List<byte[]> byteList) {
        int size = byteList.stream().mapToInt((a) -> a.length).sum();

        ByteBuffer buffer = ByteBuffer.allocate(size);
        for (var i : byteList) {
            buffer.put(i);
        }
        return buffer.array();
    }

    public static List<byte[]> separate(byte[] source) {

        List<byte[]> resList = new ArrayList<>();

        int start = 0;

        for (int i = 0; i < Math.ceil(source.length / (float) Frame.MAX_SIZE) - 1; i++) {
            resList.add(
                    Arrays.copyOfRange(source, start, (start + Frame.MAX_SIZE))
            );
            start += Frame.MAX_SIZE;
        }
        resList.add(
                Arrays.copyOfRange(source, start, source.length)
        );
        return resList;
    }


}
