package ru.bardinpetr.itmo.lab5.network.app.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.network.app.AppResponseController;
import ru.bardinpetr.itmo.lab5.network.app.session.Session;

/**
 * Envelope for handling request during application chain processing
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppRequest {
    private ReqStatus status = ReqStatus.UNKNOWN;
    private Long id;
    private Session<?> session;
    private AppResponseController response;
    private APICommand payload;

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
