package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.main;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.models.factory.ModelProvider;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.bottom.BottomPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.map.WorkersMapPage;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.show.OrganizationShowPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.script.ScriptPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.userInfo.UsersInfoZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show.WorkerShowPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.events.client.consumers.ResourceEventConsumer;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetSelfInfoCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.InfoCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.ShowMineCommand;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

import static javax.swing.SwingUtilities.invokeLater;

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
        setSize(new Dimension(800, 500));
        setVisible(true);

    }

    protected void initComponents() {
        mainPanel = new JPanel();
        bottomMenu = new BottomPanelZ();
        upperPanel = new JPanel();
        menuBar = new JMenuBar();
        workersMenuButton = new JMenuItem("", SwingConstants.EAST);
        orgsMenuButton = new JMenuItem("", SwingConstants.CENTER);
        mapMenuButton = new JMenuItem("", SwingConstants.CENTER);
        scriptMenuButton = new JMenuItem("", SwingConstants.CENTER);
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
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.add(new WorkerShowPanelZ(), "WORKERS");
        mainPanel.add(new OrganizationShowPanel(), "ORGANIZATIONS");
        mainPanel.add(new WorkersMapPage(ModelProvider.workers()), "MAP");
        mainPanel.add(new ScriptPanel(), "SCRIPT");


        workersMenuButton.addActionListener((e) -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "WORKERS");
        });

        orgsMenuButton.addActionListener((e) -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "ORGANIZATIONS");
        });

        mapMenuButton.addActionListener((e) -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "MAP");
        });
        scriptMenuButton.addActionListener((e) -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "SCRIPT");
        });


        initComponentsI18n();
        pack();
        setLocationRelativeTo(getOwner());
        invokeLater(() -> {
            new APICommandMenger().sendCommand(
                    new GetSelfInfoCommand(),
                    this,
                    "MainFrame.canNotGetUsernameError.text",
                    (response) -> {
                        var resp = (GetSelfInfoCommand.GetSelfInfoResponse) response;
                        usersInfo.setUsername(resp.getUsername());
                    }
            );
        });
        invokeLater(this::updateInformation);
        APIProvider.getPoolingEventSource().subscribe(new ResourceEventConsumer(
                (e -> updateInformation()),
                "worker"
        ));
    }

    public void updateInformation() {
        new APICommandMenger().sendCommand(
                new ShowMineCommand(),
                this,
                "MainFrame.canNotGetMineWorkers.text",
                (response) -> {
                    var resp = (ShowMineCommand.ShowCommandResponse) response;
                    usersInfo.setWorkersCount(resp.getResult().size());
                }
        );

        new APICommandMenger().sendCommand(
                new InfoCommand(),
                this,
                "MainFrame.canNotFetInfo.text",
                (response) -> {
                    var resp = (InfoCommand.InfoCommandResponse) response;
                    bottomMenu.setInitDate(resp.getResult().getInitializationDate());
                    bottomMenu.setBDSize(resp.getResult().getItemsCount());
                }
        );
    }

    protected void initComponentsI18n() {
        if (workersMenuButton == null) return;
        ResourceBundle bundle = getResources();
        setTitle(bundle.getString("MainFrame.title"));
        workersMenuButton.setText(bundle.getString("MainFrame.workersMenuButton.text"));
        orgsMenuButton.setText(bundle.getString("MainFrame.orgsMenuButton.text"));
        mapMenuButton.setText(bundle.getString("MainFrame.mapMenuButton.text"));
        scriptMenuButton.setText(bundle.getString("MainFrame.scriptMenuButton.text"));
    }
}
