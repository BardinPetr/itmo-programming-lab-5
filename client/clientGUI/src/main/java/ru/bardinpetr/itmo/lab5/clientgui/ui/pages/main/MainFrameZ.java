package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.main;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.bottom.component.BottomPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.show.OrganizationShowPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.userInfo.UsersInfoZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show.WorkerShowPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrameZ;
import ru.bardinpetr.itmo.lab5.models.commands.api.ExecuteScriptCommand;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
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
    final JFileChooser scriptChooser = new JFileChooser();
    private String scriptChooseText = getResources().getString("MainFrame.scriptChooseText.text");

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
        mainPanel.add(new OrganizationShowPanel(), "ORGANIZATIONS");

        scriptChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".zb");
            }
            @Override
            public String getDescription() {
                return getResources().getString("MainFrame.scriptChooseText.description");
            }
        });

        workersMenuButton.addActionListener((e) -> {
            CardLayout cl = (CardLayout)(mainPanel.getLayout());
            cl.show(mainPanel, "WORKERS");
        });

        orgsMenuButton.addActionListener((e) -> {
            CardLayout cl = (CardLayout)(mainPanel.getLayout());
            cl.show(mainPanel, "ORGANIZATIONS");
        });

        mapMenuButton.addActionListener((e) -> {
            //TODO show map panel
        });

        scriptMenuButton.addActionListener((e) -> {
            scriptChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            scriptChooser.showDialog(mainPanel, scriptChooseText);
        });

        scriptChooser.addActionListener((e1 -> {
            var scriptPath = scriptChooser.getSelectedFile().getPath();
            //TODO execute script
        }));

        initComponentsI18n();
        pack();
        setLocationRelativeTo(getOwner());
    }
    protected void initComponentsI18n() {
        if (workersMenuButton==null)return;
        ResourceBundle bundle = getResources();
        scriptChooseText = getResources().getString("MainFrame.scriptChooseText.text");
        workersMenuButton.setText(bundle.getString("MainFrame.workersMenuButton.text"));
        orgsMenuButton.setText(bundle.getString("MainFrame.orgsMenuButton.text"));
        mapMenuButton.setText(bundle.getString("MainFrame.mapMenuButton.text"));
        scriptMenuButton.setText(bundle.getString("MainFrame.scriptMenuButton.text"));
        scriptChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".zb");
            }
            @Override
            public String getDescription() {
                return getResources().getString("MainFrame.scriptChooseText.description");
            }
        });
    }

}
