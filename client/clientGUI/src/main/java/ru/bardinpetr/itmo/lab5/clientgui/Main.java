package ru.bardinpetr.itmo.lab5.clientgui;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.api.APIConnectorFactory;
import ru.bardinpetr.itmo.lab5.clientgui.ui.MainFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.login.LoginPage;

public class Main {
    public static void main(String[] args) {
        APIConnectorFactory.create();

        new LoginPage(() -> {
            System.out.println(APIProvider.getCredentialsStorage().getCredentials());
            new MainFrame();
        });
    }
}