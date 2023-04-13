package ru.bardinpetr.itmo.lab5.client.ui.cli;

import ru.bardinpetr.itmo.lab5.client.controller.common.handlers.ClientCommandResponse;
import ru.bardinpetr.itmo.lab5.client.controller.common.handlers.UICallableCommand;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ScriptException;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.responses.UserPrintableAPICommandResponse;

import java.util.List;

/**
 * Class for running local commands in UI and parsing their return values and handle errors
 */
public class UICommandInvoker {

    private final UIReceiver screenUIReceiver;

    /**
     * @param screenUIReceiver UIReceiver for outputting results and errors to user
     */
    public UICommandInvoker(UIReceiver screenUIReceiver) {
        this.screenUIReceiver = screenUIReceiver;
    }

    /**
     * Call command and catch all exceptions printing them as CommandResponse.
     *
     * @param command command object
     * @param args    command argument from CLI
     * @return true if command succeeded
     * @throws ScriptException this exception is passed to the root of nested scripts and only there should be handled
     */
    public boolean invoke(UICallableCommand command, List<String> args) throws ScriptException {
        ClientCommandResponse<? extends UserPrintableAPICommandResponse> resp;
        try {
            resp = command.executeWithArgs(args);
        } catch (ScriptException ex) {
            throw ex; // Should be handled by ScriptExecutor and ScriptLocalCommand
        } catch (Exception ex) {
            resp = ClientCommandResponse.error(ex.getMessage());
        }
        if (resp == null) resp = ClientCommandResponse.ok();

        if (args.size() > 0)
            print(args.get(0), resp);
        else
            print(null, resp);

        return resp.isSuccess();
    }

    /**
     * Print to ui command's response as payload, text message or ok/error
     *
     * @param caller name of called function or null to ignore
     * @param result response of command
     */
    protected void print(String caller, ClientCommandResponse<? extends UserPrintableAPICommandResponse> result) {
        if (result.isSuccess()) {
            var msg = result.message();
            if (result.payload() != null) {
                screenUIReceiver.display(result.payload().getUserMessage());
            } else if (msg != null && !msg.isEmpty()) {
                screenUIReceiver.display(msg);
            } else if (caller != null) {
                screenUIReceiver.ok(caller);
            } else {
                screenUIReceiver.ok();
            }
        } else {
            screenUIReceiver.error("[payload: %s] %s".formatted(caller == null ? "" : caller, result.message()));
        }
    }
}
