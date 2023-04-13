package ru.bardinpetr.itmo.lab5.network.utils;

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

    public static byte[] joinFromFrames(List<Frame> frameList) {
        List<byte[]> byteList = new ArrayList<>();
        for (var i : frameList) {
            byteList.add(i.getPayload());
        }
        return join(byteList);
    }

    private static byte[] join(List<byte[]> byteList) {
        int size = byteList.stream().mapToInt((a) -> a.length).sum();

        ByteBuffer buffer = ByteBuffer.allocate(size);
        for (var i : byteList) {
            buffer.put(i);
        }
        return buffer.array();
    }


    public static List<Frame> separateToFrames(byte[] source) {
        var byteList = separate(source);
        List<Frame> resList = new ArrayList<>();

        for (int i = 0; i < byteList.size(); i++) {
            resList.add(new Frame(
                    i + 2,
                    byteList.get(i)
            ));
        }
        return resList;
    }

    private static List<byte[]> separate(byte[] source) {

        List<byte[]> resList = new ArrayList<>();

        int start = 0;

        for (int i = 0; i < Math.ceil(source.length / (float) Frame.PAYLOAD_SIZE) - 1; i++) {
            resList.add(
                    Arrays.copyOfRange(source, start, (start + Frame.PAYLOAD_SIZE))
            );
            start += Frame.PAYLOAD_SIZE;
        }
        resList.add(
                Arrays.copyOfRange(source, start, source.length)
        );
        return resList;
    }


}
