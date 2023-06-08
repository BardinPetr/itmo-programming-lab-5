package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.main;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.bottom.component.BottomPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.userInfo.UsersInfoZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show.WorkerShowPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrameZ;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class MainFrameZ extends ResourcedFrame {
    private JPanel mainPanel;
    private BottomPanelZ bottomMenu;
    private JPanel upperPanel;
    private JMenuBar menuBar;
    private JMenuItem workersMenuButton;
    private JMenuItem orgsMenuButton;
    private JMenuItem mapMenuButton;
    private JMenuItem scriptMenuButton;
    private UsersInfoZ usersInfo;

    public MainFrameZ() {
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        mainPanel = new JPanel();
        bottomMenu = new BottomPanelZ();
        upperPanel = new JPanel();
        menuBar = new JMenuBar();
        workersMenuButton = new JMenuItem();
        orgsMenuButton = new JMenuItem();
        mapMenuButton = new JMenuItem();
        scriptMenuButton = new JMenuItem();
        usersInfo = new UsersInfoZ();

        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        menuBar.add(workersMenuButton);
        menuBar.add(orgsMenuButton);
        menuBar.add(mapMenuButton);
        menuBar.add(scriptMenuButton);
        
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(menuBar, BorderLayout.WEST);
        upperPanel.add(usersInfo, BorderLayout.EAST);

        contentPane.add(upperPanel, BorderLayout.NORTH);
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(bottomMenu, BorderLayout.SOUTH);

        mainPanel.setLayout(new CardLayout());
        mainPanel.add(new WorkerShowPanelZ(), "WORKERS");
        mainPanel.add(new UsersInfoZ(), "USERS");




        workersMenuButton.addActionListener((e) -> {
            CardLayout cl = (CardLayout)(mainPanel.getLayout());
            cl.show(mainPanel, "USERS");
            System.out.println(e);
        });

        initComponentsI18n();
        pack();
        setLocationRelativeTo(getOwner());
    }
    protected void initComponentsI18n() {
        if (workersMenuButton==null)return;
        ResourceBundle bundle = getResources();
        workersMenuButton.setText(bundle.getString("MainFrame.workersMenuButton.text"));
        orgsMenuButton.setText(bundle.getString("MainFrame.orgsMenuButton.text"));
        mapMenuButton.setText(bundle.getString("MainFrame.mapMenuButton.text"));
        scriptMenuButton.setText(bundle.getString("MainFrame.scriptMenuButton.text"));
    }

}
