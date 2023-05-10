package ru.bardinpetr.itmo.lab5.network.app.server;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APIResponseStatus;
import ru.bardinpetr.itmo.lab5.network.app.server.errors.ApplicationBuildException;
import ru.bardinpetr.itmo.lab5.network.app.server.interfaces.handlers.IApplicationCommandHandler;
import ru.bardinpetr.itmo.lab5.network.app.server.interfaces.types.IFilteredApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.models.requests.AppRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Server and client side API extensible controller.
 * Used to be build a request-reply layer over any transport.
 * It is symmetrical, so handling commands on server ends with sending response and on client - with ACK.
 * Moreover, if supported by underlying channel, could be used to make server able to call client at any time within active session.
 * Session handling is not included by default and should be provided via inheritor of SourcingAPIApplication
 */
@Slf4j
public abstract class AbstractApplication implements IFilteredApplication {

    private final List<AbstractApplication> processors = new ArrayList<>();
    private final Map<Class<? extends APICommand>, IApplicationCommandHandler> commandHandlers = new HashMap<>();
    private IApplicationCommandHandler anyCommandHandler;

    public AbstractApplication() {

    }

    /**
     * Processes incoming request firstly using nested applications
     * then may be terminated by any of local on() handlers with precedence of single-command handlers.
     * If nested app terminated request or any local terminating handler exist, no further processing done
     *
     * @param request applications request object
     * @return processed request
     */
    protected AppRequest process(AppRequest request) {
        if (!filter(request)) {
            log.debug("Message {} ignored by {}", request.id(), getClass().getSimpleName());
            return request;
        }

        log.debug("Processing message at {}: {}", getClass().getSimpleName(), request);

        safeProcessCall(request, this::beforeAll);
        if (request.isTerminated()) return request;

        for (var app : processors) {
            safeProcessCall(request, app::process);
            if (request.isTerminated()) return request;
        }

        safeProcessCall(request, this::beforeTermination);
        if (request.isTerminated()) return request;

        var payload = request.payload();
        if (payload == null)
            throw new RuntimeException("Payload null");

        var terminatingHandler = commandHandlers.get(payload.getCmdIdentifier());
        if (terminatingHandler != null) {
            safeProcessCall(request, terminatingHandler::handle);
            request.terminate();
        } else if (anyCommandHandler != null) {
            safeProcessCall(request, anyCommandHandler::handle);
            request.terminate();
        }

        log.debug("Message {} left {}", request.id(), getClass().getSimpleName());
        return request;
    }

    /**
     * Method called by process() before any delegation
     *
     * @param request request to be processed
     */
    protected void beforeAll(AppRequest request) {
    }

    /**
     * Method called by process() after aby nested application but before self terminating methods
     *
     * @param request request to be processed
     */
    protected void beforeTermination(AppRequest request) {
    }

    /**
     * Call another application and handle exceptions with sending error response
     *
     * @param req request to be processed
     * @param app application's process method
     */
    private void safeProcessCall(AppRequest req, Consumer<AppRequest> app) {
        try {
            app.accept(req);
        } catch (Throwable ex) {
            req
                    .response()
                    .status(APIResponseStatus.SERVER_ERROR)
                    .message(ex.getMessage())
                    .send();
        }
    }

    /**
     * Add application to chain to last position
     * All requests will go through app-chain and then processed by this app
     */
    public final void use(AbstractApplication app) {
        processors.add(app);
    }

    /**
     * Register command handler
     *
     * @param cmd     command to handle
     * @param handler callable
     */
    public final void on(Class<? extends APICommand> cmd, IApplicationCommandHandler handler) {
        if (commandHandlers.containsKey(cmd))
            throw new ApplicationBuildException("Commands should be handled once only");
        commandHandlers.put(cmd, handler);
    }

    /**
     * Register one command handler for multiple commands
     *
     * @param cmds    command list to handle
     * @param handler callable
     */
    public final void on(IApplicationCommandHandler handler, Class<? extends APICommand>... cmds) {
        for (var i : cmds)
            commandHandlers.put(i, handler);
    }

    /**
     * Subscribe on any incoming request
     *
     * @param handler callable
     */
    public final void on(IApplicationCommandHandler handler) {
        if (anyCommandHandler != null)
            throw new ApplicationBuildException("Only one global handler should exist");
        anyCommandHandler = handler;
    }

    public void start() {
    }
}
