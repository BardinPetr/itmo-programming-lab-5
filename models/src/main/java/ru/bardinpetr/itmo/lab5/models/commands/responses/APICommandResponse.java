package ru.bardinpetr.itmo.lab5.models.commands.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response for command. Payload should be specified by inheriting this class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APICommandResponse implements UserPrintableAPICommandResponse {
    private boolean success = true;
    private boolean resolved = true;
    private String textualResponse = null;

    /**
     * Instantiate Response class when responding to succeeded action with filling in payload.
     *
     * @return created response object
     */
    public static APICommandResponse ok() {
        return new APICommandResponse(true, true, "ok");
    }

    /**
     * Instantiate Response class when responding to failed action with textual message
     *
     * @param text string message to return to client
     * @return created response object
     */
    public static APICommandResponse error(String text) {
        return new APICommandResponse(false, true, text);
    }

    /**
     * Instantiate Response class when responding to failed action with textual message
     *
     * @param cause exception which message will be sent to client
     * @return created response object
     */
    public static APICommandResponse error(Exception cause) {
        return new APICommandResponse(false, true, cause.toString());
    }

    /**
     * Instantiate Response class when responding to action executor for which was not resolved on server
     *
     * @return created response object
     */
    public static APICommandResponse noResolve() {
        return new APICommandResponse(false, false, "no command implementation on server");
    }
}
