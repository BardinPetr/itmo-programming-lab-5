package ru.bardinpetr.itmo.lab5.clientgui.ui;


import ru.bardinpetr.itmo.lab5.clientgui.ui.components.bottom.component.BottomPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show.WorkerShowPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.main.MainFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrame;

import javax.swing.*;

public class TestInterface {
    public static void main(String[] args) {
        var mainFrame = new JFrame();
        var t = new WorkerShowPanelZ();
        mainFrame.add(t);
        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}
