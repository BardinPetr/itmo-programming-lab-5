package ru.bardinpetr.itmo.lab5.network.transport.server.multithreading;

import ru.bardinpetr.itmo.lab5.network.transport.errors.TransportException;
import ru.bardinpetr.itmo.lab5.network.transport.models.Frame;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.RecursiveAction;

public class Receiver extends RecursiveAction {
    private final Pipe pipe;
    private final Pipe.SourceChannel sourceChannel;


    public Receiver(Pipe pipe) {
        this.pipe = pipe;
        this.sourceChannel = pipe.source();
    }

    @Override
    protected void compute() {
        try {
            var initFrame = Frame.fromChannel(sourceChannel);
            int len = ByteBuffer.wrap(initFrame.getPayload()).getInt();

            System.out.println(len);


        } catch (IOException e) {
            throw new TransportException(e);
        }

    }
}
