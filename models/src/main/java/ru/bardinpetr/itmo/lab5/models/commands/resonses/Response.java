package ru.bardinpetr.itmo.lab5.models.commands.resonses;


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
public class Response<T extends ICommandResponse> {
    private boolean success = true;
    private String text = null;
    private T payload;

    /**
     * Instantiate Response class when responding to succeeded action with filling in payload.
     *
     * @param payload message payload
     * @param <T>     payload type
     * @return created response object
     */
    public static <T extends ICommandResponse> Response<T> success(T payload) {
        return new Response<>(true, null, payload);
    }

    /**
     * Instantiate Response class when responding to failed action with textual message
     *
     * @param text string message to return to client
     * @return created response object
     */
    public static <T extends ICommandResponse> Response<T> error(String text) {
        return new Response<>(false, text, null);
    }

    /**
     * Instantiate Response class when responding to failed action with textual message
     *
     * @param cause exception which message will be sent to client
     * @return created response object
     */
    public static <T extends ICommandResponse> Response<T> error(Exception cause) {
        return new Response<>(false, cause.toString(), null);
    }
}
