package ru.bardinpetr.itmo.lab5.network.session.models;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.network.models.BaseMessage;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;

/**
 * Handles session with other party
 *
 * @param <K> type for object uniquely identifying target
 */
@Data
public class Session<K> {
    private final K address;
    private Long recipientMessageCounter = 0L;
    private Long selfMessageCounter = 0L;
    private State state = State.CREATED;

    public SocketMessage prepareSend(BaseMessage source) {
        selfMessageCounter++;
        return new SocketMessage(
                source.getCmdType(),
                selfMessageCounter,
                0L,
                false,
                source.getPayload()
        );
    }

    public SocketMessage prepareSendReply(Long replyOn, BaseMessage source) {
        selfMessageCounter++;
        return new SocketMessage(
                source.getCmdType(),
                selfMessageCounter,
                replyOn,
                false,
                source.getPayload()
        );
    }

    public IncomingMessageState registerIncoming(SocketMessage message) {
        var id = message.getId();

        if (message.getCmdType() == SocketMessage.CommandType.INIT) {
            recipientMessageCounter = id;
            return IncomingMessageState.HEAD;
        }

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

    public enum State {
        CREATED,
        WAITING_HANDSHAKE,
        OPERATING,
        DEAD
    }
}
