package ru.bardinpetr.itmo.lab5.network.session;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;

/**
 * Handles session with other party
 *
 * @param <K> type for object uniquely identifying target
 */
@Data
public class Session<K> {
    private Long recipientMessageCounter = 0L;
    private Long selfMessageCounter = 0L;
    private K address;

    public SocketMessage prepareSend(SocketMessage source) {
        return source.withId(selfMessageCounter++);
    }

    public IncomingMessageState registerIncoming(SocketMessage message) {
        var id = message.getId();
        if (id == recipientMessageCounter) {
            recipientMessageCounter++;
            return IncomingMessageState.HEAD;
        } else if (id < recipientMessageCounter) {
            return IncomingMessageState.DUPLICATE;
        }
        return IncomingMessageState.EARLY;
    }

    public enum IncomingMessageState {
        HEAD,
        DUPLICATE,
        EARLY
    }
}
