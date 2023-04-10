package ru.bardinpetr.itmo.lab5.network.app;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.commands.requests.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.errors.ApplicationBuildException;
import ru.bardinpetr.itmo.lab5.network.app.interfaces.IApplicationCommandHandler;
import ru.bardinpetr.itmo.lab5.network.app.interfaces.IFilteredApplication;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Server and client side API extensible controller.
 * Used to be build a request-reply layer over any transport.
 * It is symmetrical, so handling commands on server ends with sending response and on client - with ACK.
 * Moreover, if supported by underlying channel, could be used to make server able to call client at any time within active session.
 * Session handling is not included by default and should be provided via inheritor of SourcingAPIApplication
 *
 * @param <S> request base type
 * @param <R> response base type
 */
@Slf4j
public abstract class APIApplication<S extends IIdentifiableCommand, R> implements IFilteredApplication {

    private final List<APIApplication<S, R>> processors = new ArrayList<>();
    private final Map<S, IApplicationCommandHandler<S, R>> commandHandlers = new HashMap<>();
    private IApplicationCommandHandler<S, R> anyCommandHandler;

    public APIApplication() {

    }

    /**
     * Processes incoming request firstly using nested applications
     * then may be terminated by any of local on() handlers with precedence of single-command handlers.
     * If nested app terminated request or any local terminating handler exist, no further processing done
     *
     * @param request applications request object
     * @return processed request
     */
    protected AppRequest<S, R> process(AppRequest<S, R> request) {
        if (!filter(request)) {
            log.debug("Message {} ignored by {}", request.id(), getClass().getSimpleName());
            return request;
        }

        log.debug("Processing message at {}: {}", getClass().getSimpleName(), request);

        for (var app : processors) {
            request = app.process(request);
            if (request.response().isTerminated())
                return request;
        }

        var terminatingHandler = commandHandlers.get(request.cmd());
        if (terminatingHandler != null) {
            terminatingHandler.handle(request);
            request.response().terminate();
        } else if (anyCommandHandler != null) {
            anyCommandHandler.handle(request);
            request.response().terminate();
        }

        return request;
    }

    public final void use(APIApplication<S, R> app) {
        processors.add(app);
    }

    public final void on(S cmd, IApplicationCommandHandler<S, R> handler) {
        if (commandHandlers.containsKey(cmd))
            throw new ApplicationBuildException("Commands should be handled once only");
        commandHandlers.put(cmd, handler);
    }

    public final void on(IApplicationCommandHandler<S, R> handler, S... cmds) {
        for (var i : cmds)
            commandHandlers.put(i, handler);
    }

    public final void on(IApplicationCommandHandler<S, R> handler) {
        if (anyCommandHandler != null)
            throw new ApplicationBuildException("Only one global handler should exist");
        anyCommandHandler = handler;
    }

    public void start() {
    }
}
