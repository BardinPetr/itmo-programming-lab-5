package ru.bardinpetr.itmo.lab5.clientgui.i18n.data;

import java.util.ListResourceBundle;

public class GUITexts extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"login", "Login"},
                {"login_btn", "Login"},
                {"register_btn", "Register"}
        };
    }
}
