package ru.bardinpetr.itmo.lab5.clientgui.i18n.data;

import java.util.ListResourceBundle;

public class GUITexts extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"loginform.label5.text", "text"},
                {"loginpage.checkBox1.text", "text"},
                {"loginpage.button2.text", "text"},
                {"loginpage.button3.text", "text"},
                {"loginpage.this.title", "Login"},
                {"loginpage.label1.text", "text"},
                {"loginpage.label2.text", "text"},
                {"loginpage.label3.text", "text"},
                {"login", "Login"},
                {"login_btn", "Login"},
                {"register_btn", "Register"}
        };
    }
}