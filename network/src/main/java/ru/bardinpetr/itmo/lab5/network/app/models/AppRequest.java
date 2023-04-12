package ru.bardinpetr.itmo.lab5.network.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.requests.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.AppResponseController;
import ru.bardinpetr.itmo.lab5.network.app.session.Session;

/**
 * Envelope for handling request during application chain processing
 *
 * @param <Q> request type
 * @param <A> response type
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppRequest<Q extends IIdentifiableCommand, A> {
    private ReqStatus status = ReqStatus.UNKNOWN;
    private Long id;
    private Session<?> session;
    private AppResponseController<?, A> response;
    private Q payload;

    public Long id() {
        return response == null ? -1 : response.getId();
    }

    public enum ReqStatus {
        UNKNOWN,
        CREATED,
        INVALID,
        NORMAL
    }
}
