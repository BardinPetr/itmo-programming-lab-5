package ru.bardinpetr.itmo.lab5.network.app;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.commands.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.errors.ApplicationBuildException;
import ru.bardinpetr.itmo.lab5.network.app.interfaces.IApplicationCommandHandler;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class ServerApplication {

    private final List<ServerApplication> processors = new ArrayList<>();
    private final Map<IIdentifiableCommand, IApplicationCommandHandler> commandHandlers = new HashMap<>();
    private IApplicationCommandHandler anyCommandHandler;

    public ServerApplication() {

    }

    /**
     * Processes incoming request firstly using nested applications
     * then may be terminated by any of local on() handlers with precedence of single-command handlers.
     * If nested app terminated request or any local terminating handler exist, no further processing done
     *
     * @param request applications request object
     * @param <K>     payload type
     * @return processed request
     */
    protected <K extends IIdentifiableCommand, R> AppRequest<K, R> process(AppRequest<K, R> request) {
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

    public final void use(ServerApplication app) {
        processors.add(app);
    }

    public final void on(IIdentifiableCommand cmd, IApplicationCommandHandler handler) {
        if (commandHandlers.containsKey(cmd))
            throw new ApplicationBuildException("Commands should be handled once only");
        commandHandlers.put(cmd, handler);
    }

    public final void on(IApplicationCommandHandler handler, IIdentifiableCommand... cmds) {
        for (var i : cmds)
            commandHandlers.put(i, handler);
    }

    public final void on(IApplicationCommandHandler handler) {
        if (anyCommandHandler != null)
            throw new ApplicationBuildException("Only one global handler should exist");
        anyCommandHandler = handler;
    }

    public void start() {

    }
}
