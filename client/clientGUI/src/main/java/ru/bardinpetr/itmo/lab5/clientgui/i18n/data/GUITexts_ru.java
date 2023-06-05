package ru.bardinpetr.itmo.lab5.clientgui.i18n.data;

import java.util.ListResourceBundle;

public class GUITexts_ru extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"login", "Логин"},
                {"login_btn", "Войти"},
                {"register_btn", "Зарегистрироваться"}
        };
    }
}
