package ru.bardinpetr.itmo.lab5.client.ui.cli;

import ru.bardinpetr.itmo.lab5.client.controller.common.CommandResponse;
import ru.bardinpetr.itmo.lab5.client.controller.common.UICallableCommand;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;

import java.util.List;

public class UICommandInvoker {

    private final UIReceiver screenUIReceiver;

    /**
     * @param screenUIReceiver UIReceiver for outputting results and errors to user
     */
    public UICommandInvoker(UIReceiver screenUIReceiver) {
        this.screenUIReceiver = screenUIReceiver;
    }

    public void invoke(UICallableCommand command, List<String> args) {
        CommandResponse resp;
        try {
            resp = command.executeWithArgs(args);
        } catch (Exception ex) {
            resp = CommandResponse.error(ex.getMessage());
        }
        if (resp == null) resp = CommandResponse.ok();

        print(resp);
    }

    protected void print(CommandResponse result) {
        if (result.isSuccess()) {
            if (result.payload() != null)
                screenUIReceiver.display(result.payload().getUserMessage());
            else
                screenUIReceiver.ok();
        } else {
            screenUIReceiver.error(result.message());
        }
    }
}
