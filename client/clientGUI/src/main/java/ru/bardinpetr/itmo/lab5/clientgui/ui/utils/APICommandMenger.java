package ru.bardinpetr.itmo.lab5.clientgui.ui.utils;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingUtilities.invokeLater;

public class APICommandMenger {
    private static APICommandMenger instance;
    private final APIClientConnector apiConnector;

    private APICommandMenger() {
        apiConnector = APIProvider.getConnector();
    }

    public static APICommandMenger getInstance() {
        if (instance == null)
            instance = new APICommandMenger();
        return instance;
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

        Runnable task = () -> {
            var resources = UIResources.getInstance();
            var validation = cmd.validate();
            if (!validation.isAllowed()) {
                Object[] options = {
                        resources.get("optionalAnswers.Ok")
                };
                JOptionPane.showOptionDialog(
                        dialogParentComponent,
                        resources.get(validation.getMsg()),
                        resources.get(validationErrorKey),
                        JOptionPane.OK_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,     //do not use a custom Icon
                        options,
                        options[0]
                );
                return;
            }

            APICommandResponse result;
            try {
                result = apiConnector.call(cmd);
            } catch (APIClientException ex) {
                Object[] options = {
                        resources.get("optionalAnswers.Ok")
                };
                JOptionPane.showOptionDialog(
                        dialogParentComponent,
                        resources.get("APICommandMenger.connectionError.error"),
                        resources.get(connectionErrorKey),
                        JOptionPane.OK_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,     //do not use a custom Icon
                        options,
                        options[0]
                );
                return;
            }

            if (!result.isSuccess()) {
                Object[] options = {
                        resources.get("optionalAnswers.Ok")
                };
                JOptionPane.showOptionDialog(
                        dialogParentComponent,
                        resources.get(result.getUserMessage()),
                        resources.get(executionErrorKey),
                        JOptionPane.OK_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,     //do not use a custom Icon
                        options,
                        options[0]
                );
                return;
            }
            handler.handle(result);
        };

//        if (!needWait) {
        invokeLater(task);
//        } else {
//            task.run();
//        }
    }

}
