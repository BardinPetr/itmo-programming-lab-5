package ru.bardinpetr.itmo.lab5.clientgui.ui.utils;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import static javax.swing.SwingUtilities.invokeAndWait;
import static javax.swing.SwingUtilities.invokeLater;

public class APICommandMenger {
    private final APIClientConnector apiConnector;

    public APICommandMenger() {
        apiConnector = APIProvider.getConnector();
    }

    private ResourceBundle getResources(){
        return UIResources.getInstance().getBundle();
    }
    public void sendCommand(
            APICommand cmd,
            Component dialogParentComponent,
            String executionErrorKey,
            ISuccessCommandHandler handler, boolean needWait) {
        sendCommand(
                cmd,
                dialogParentComponent,
                "command.error.invalidField",
                "command.error.requestFailed",
                executionErrorKey,
                handler,
                needWait
        );
    }

    public void sendCommand(
            APICommand cmd,
            Component dialogParentComponent,
            String executionErrorKey,
            ISuccessCommandHandler handler) {
        sendCommand(
                cmd,
                dialogParentComponent,
                "command.error.invalidField",
                "command.error.requestFailed",
                executionErrorKey,
                handler,
                false
        );
    }

    public void sendCommand(
            APICommand cmd,
            Component dialogParentComponent,
            String validationErrorKey,
            String connectionErrorKey,
            String executionErrorKey,
            ISuccessCommandHandler handler, boolean needWait) {

        Runnable task = ()-> {
            var validation = cmd.validate();
            if (!validation.isAllowed()) {
                JOptionPane.showMessageDialog(
                        dialogParentComponent,
                        getResources().getString(validation.getMsg()),
                        getResources().getString(validationErrorKey),
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            APICommandResponse result;
            try {
                result = apiConnector.call(cmd);
            } catch (APIClientException ex) {
                JOptionPane.showMessageDialog(
                        dialogParentComponent,
                        getResources().getString(ex.getMessage()),
                        getResources().getString(connectionErrorKey),
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (!result.isSuccess()) {
                JOptionPane.showMessageDialog(
                        dialogParentComponent,
                        getResources().getString(result.getUserMessage()),
                        getResources().getString(executionErrorKey),
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            handler.handle(result);
        };

        if (!needWait)
            invokeLater(task);
        else {
            task.run();
        }
    }

}
