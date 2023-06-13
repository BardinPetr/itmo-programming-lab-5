package ru.bardinpetr.itmo.lab5.clientgui;

import ru.bardinpetr.itmo.lab5.clientgui.api.APIConnectorFactory;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.login.LoginPage;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.main.MainFrameZ;

public class Main {
    public static void main(String[] args) {
        APIConnectorFactory.create();
        UIResources.getInstance();

        new LoginPage(MainFrameZ::new);
    }
}