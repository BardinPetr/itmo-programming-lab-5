package ru.bardinpetr.itmo.lab5.network.app.server.requests;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.network.app.server.models.Session;

/**
 * Envelope for handling request during application chain processing
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppRequest {
    private ReqStatus status = ReqStatus.UNKNOWN;
    private Long id;
    private Session<?> session;
    private AppResponseController<?> response;
    private APICommand payload;

    public ReqStatus status() {
        return status;
    }

    public Long id() {
        return id;
    }

    public Session<?> session() {
        return session;
    }

    public AppResponseController<?> response() {
        return response;
    }

    public APICommand payload() {
        return payload;
    }

    public void setResponse(AppResponseController<?> response) {
        this.response = response;
    }

    public boolean isTerminated() {
        return response.isTerminated();
    }

    public void setStatus(ReqStatus newStatus) {
        status = newStatus;
    }

    public enum ReqStatus {
        UNKNOWN,
        CREATED,
        INVALID,
        NORMAL
    }
}
