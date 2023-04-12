package ru.bardinpetr.itmo.lab5.models.commands.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.IAPIMessage;

/**
 * Response for command. Payload should be specified by inheriting this class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APICommandResponse implements UserPrintableAPICommandResponse, IAPIMessage {
    private Status status = Status.OK;
    private String textualResponse = null;

    /**
     * Instantiate Response class when responding to succeeded action with filling in payload.
     *
     * @return created response object
     */
    public static APICommandResponse ok() {
        return new APICommandResponse(Status.OK, "ok");
    }

    /**
     * Instantiate Response class when responding to failed action with textual message
     *
     * @param text string message to return to client
     * @return created response object
     */
    public static APICommandResponse error(String text) {
        return new APICommandResponse(Status.CLIENT_ERROR, text);
    }

    /**
     * Instantiate Response class when responding to failed action with textual message
     *
     * @param cause exception which message will be sent to client
     * @return created response object
     */
    public static APICommandResponse error(Exception cause) {
        return new APICommandResponse(Status.CLIENT_ERROR, cause.toString());
    }

    /**
     * Instantiate Response class when responding to action executor for which was not resolved on server
     *
     * @return created response object
     */
    public static APICommandResponse noResolve() {
        return new APICommandResponse(Status.CLIENT_ERROR, "no command implementation on server");
    }

    public boolean isSuccess() {
        return status == Status.OK;
    }

    public boolean isResolved() {
        return status != Status.NOT_FOUND;
    }

    public enum Status {
        OK,
        CLIENT_ERROR,
        SERVER_ERROR,
        NOT_FOUND
    }
}
