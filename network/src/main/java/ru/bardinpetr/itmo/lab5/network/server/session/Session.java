package ru.bardinpetr.itmo.lab5.network.server.session;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.network.framelevel.Frame;

import java.net.SocketAddress;
import java.nio.channels.Pipe;
import java.util.List;

@Data
public class Session {
    private Status status;
    private Frame[] receiveFrameList;

    private List<Frame> sendFrameList;

    private SocketAddress consumerAddress;
    private Pipe pipe;

    public Session(SocketAddress consumerAddress, Status status, Pipe pipe) {
        this.pipe = pipe;
        this.consumerAddress = consumerAddress;
        this.status = status;
    }

    public Session(SocketAddress consumerAddress, Status status, Pipe pipe, List<Frame> sendList) {
        this(consumerAddress, status, pipe);
        sendFrameList = sendList;
    }

    public static Session getNetworkSession() {
        return new Session(null, Status.NETWORK, null);
    }

    public boolean checkFinishReading() {
        for (var i : receiveFrameList) {
            if (i == null) return false;
        }
        return true;
    }

    public boolean addToList(Frame frame) {
        int pos = (int) frame.getId() - 2;
        if (receiveFrameList[pos] != null) return false;

        receiveFrameList[pos] = frame;
        return true;
    }

}
