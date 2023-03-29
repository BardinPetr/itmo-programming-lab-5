package ru.bardinpetr.itmo.lab5.models.commands.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response for command. Contains payload which must also be specified in Command
 *
 * @param <T> payload type
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T extends APICommandResponse> {
    private boolean success = true;
    private boolean resolved = true;
    private String text = null;
    private T payload;

    /**
     * Instantiate Response class when responding to succeeded action with filling in payload.
     *
     * @param payload message payload
     * @param <T>     payload type
     * @return created response object
     */
    public static <T extends APICommandResponse> Response<T> success(T payload) {
        return new Response<>(true, true, null, payload);
    }

    /**
     * Instantiate Response class when responding to failed action with textual message
     *
     * @param text string message to return to client
     * @return created response object
     */
    public static <T extends APICommandResponse> Response<T> error(String text) {
        return new Response<>(false, true, text, null);
    }

    /**
     * Instantiate Response class when responding to failed action with textual message
     *
     * @param cause exception which message will be sent to client
     * @return created response object
     */
    public static <T extends APICommandResponse> Response<T> error(Exception cause) {
        return new Response<>(false, true, cause.toString(), null);
    }

    /**
     * Instantiate Response class when responding to action executor for which was not resolved on server
     *
     * @return created response object
     */
    public static <T extends APICommandResponse> Response<T> noResolve() {
        return new Response<>(false, false, "no command implementation on server", null);
    }
}
