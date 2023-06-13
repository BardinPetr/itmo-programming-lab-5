package ru.bardinpetr.itmo.lab5.clientgui.api;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

import javax.swing.*;

public class UIReloginAPIConnector implements APIClientConnector {

    private final APIClientConnector decoratee;
    private Runnable onLogout;

    public UIReloginAPIConnector(APIClientConnector decoratee) {
        this.decoratee = decoratee;
    }

    @Override
    public APICommandResponse call(APICommand cmd) throws APIClientException {
        return decoratee.call(cmd);
    }

    @Override
    public void registerOnLogoutListener(Runnable x) {
        onLogout = x;
    }

    @Override
    public void logout() {
        if (onLogout != null)
            SwingUtilities.invokeLater(onLogout);
    }
}
