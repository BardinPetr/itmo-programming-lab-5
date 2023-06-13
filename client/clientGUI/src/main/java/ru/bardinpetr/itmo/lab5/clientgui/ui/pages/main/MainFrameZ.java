package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.main;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
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
import java.util.List;

import static javax.swing.SwingConstants.CENTER;
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
    private APICommandMenger apiManager;
    private String scriptCard;

    public MainFrameZ() {
        setBackground(Color.WHITE);
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(800, 500));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    protected void initComponents() {
        apiManager = APICommandMenger.getInstance();
        mainPanel = new JPanel();
        bottomMenu = new BottomPanelZ();
        upperPanel = new JPanel();
        menuBar = new JMenuBar();

        workersMenuButton = new JMenuItem("", SwingConstants.CENTER);
        orgsMenuButton = new JMenuItem("", SwingConstants.CENTER);
        mapMenuButton = new JMenuItem("", SwingConstants.CENTER);
        scriptMenuButton = new JMenuItem("", SwingConstants.CENTER);

        var menuButtons = List.of(workersMenuButton, orgsMenuButton, mapMenuButton, scriptMenuButton);
        var menuButtonKeys = List.of("WORKERS", "ORGANIZATIONS", "MAP", "SCRIPT");

        var buttonBorder = BorderFactory.createRaisedBevelBorder();
        var buttonPressedBorder = BorderFactory.createLoweredBevelBorder();

        for (int i = 0; i < menuButtons.size(); i++) {
            var buttonKey = menuButtonKeys.get(i);
            var b = menuButtons.get(i);
            b.setHorizontalAlignment(CENTER);
            b.setBorder(buttonBorder);
            b.addActionListener(
                    e -> {
                        scriptCard = buttonKey;
                        ((CardLayout) mainPanel.getLayout())
                                .show(mainPanel, buttonKey);

                        menuButtons.forEach(
                                cur -> cur.setBorder(cur == b ? buttonPressedBorder : buttonBorder)
                        );
                        b.setBackground(null);
                    }
            );
            menuBar.add(b);
        }
        workersMenuButton.setBorder(buttonPressedBorder);

        usersInfo = new UsersInfoZ();

        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(menuBar, BorderLayout.WEST);
        upperPanel.add(usersInfo, BorderLayout.EAST);

        contentPane.add(upperPanel, BorderLayout.NORTH);
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(bottomMenu, BorderLayout.SOUTH);

        mainPanel.setLayout(new CardLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

//        invokeLater(() -> {
        mainPanel.add(new WorkerShowPanelZ(), "WORKERS");
        mainPanel.add(new OrganizationShowPanel(), "ORGANIZATIONS");
        mainPanel.add(new WorkersMapPage(ModelProvider.getInstance().workers()), "MAP");
        mainPanel.add(new ScriptPanel((e) -> {
            if (e && !scriptCard.equals("SCRIPT")) {
                scriptMenuButton.setBackground(new Color(135, 206, 235));
            }
        }), "SCRIPT");
//        });

        initComponentsI18n();
        setLocationRelativeTo(getOwner());

        new Thread(this::loadData).start();
    }

    private void loadData() {
        invokeLater(
                () -> apiManager.sendCommand(
                        new GetSelfInfoCommand(),
                        this,
                        "MainFrame.canNotGetUsernameError.text",
                        (response) -> {
                            var resp = (GetSelfInfoCommand.GetSelfInfoResponse) response;
                            usersInfo.setUsername(resp.getUsername());
                        }
                )
        );
        invokeLater(this::updateInformation);
        APIProvider.getPoolingEventSource().subscribe(new ResourceEventConsumer((e -> updateInformation()), "worker"));
    }

    private void updateInformation() {
        apiManager.sendCommand(
                new ShowMineCommand(),
                this,
                "MainFrame.canNotGetMineWorkers.text",
                (response) -> {
                    var resp = (ShowMineCommand.ShowCommandResponse) response;
                    usersInfo.setWorkersCount(resp.getResult().size());
                }
        );

        apiManager.sendCommand(
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
        UIResources resources = getResources();
        setTitle(resources.get("MainFrame.title"));
        workersMenuButton.setText(resources.get("MainFrame.workersMenuButton.text"));
        orgsMenuButton.setText(resources.get("MainFrame.orgsMenuButton.text"));
        mapMenuButton.setText(resources.get("MainFrame.mapMenuButton.text"));
        scriptMenuButton.setText(resources.get("MainFrame.scriptMenuButton.text"));
    }
}
