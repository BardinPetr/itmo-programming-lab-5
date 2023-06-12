package ru.bardinpetr.itmo.lab5.clientgui.ui.components.bottom;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.lang.LanguageChanger;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.commands.api.InfoCommand;

import javax.swing.*;
import java.awt.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class BottomPanelZ extends ResourcedPanel {
    private CollectionInfoPanel infoPanel;
    private LanguageChanger langLayout;

    public BottomPanelZ() {
        initComponents();
        setVisible(true);
    }

    @Override
    protected void initComponentsI18n() {

    }

    protected void initComponents() {
        infoPanel = new CollectionInfoPanel();
        langLayout = new LanguageChanger();



        setLayout(new BorderLayout());
        add(infoPanel, BorderLayout.WEST);
        add(langLayout, BorderLayout.EAST);

        initComponentsI18n();
        setVisible(true);
    }

    public void setInitDate(ZonedDateTime date){
        infoPanel.setInitDate(date);
    }

    public void setBDSize(Integer size){
        infoPanel.setBDSize(size);
    }
}
