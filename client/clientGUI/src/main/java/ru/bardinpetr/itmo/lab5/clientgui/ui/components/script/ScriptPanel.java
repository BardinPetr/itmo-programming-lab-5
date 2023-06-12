package ru.bardinpetr.itmo.lab5.clientgui.ui.components.script;

import ru.bardinpetr.itmo.lab5.client.api.auth.impl.RAMCredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.api.connectors.net.UDPAPIClientFactory;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.JWTAuthConnector;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.client.ui.cli.CLIController;
import ru.bardinpetr.itmo.lab5.client.ui.cli.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.ui.cli.UICommandInvoker;
import ru.bardinpetr.itmo.lab5.client.ui.cli.UIProvider;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.mainclient.api.commands.UserAPICommandRegistry;
import ru.bardinpetr.itmo.lab5.mainclient.api.commands.UserAPICommandsDescriptionHolder;
import ru.bardinpetr.itmo.lab5.mainclient.local.controller.registry.MainClientCommandRegistry;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app.jwt.JWTAPICommandAuthenticator;


import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

import static javax.swing.SwingUtilities.invokeLater;

public class ScriptPanel extends ResourcedPanel {
    private JFileChooser scriptChooser;

    private JTextArea resultTextArea;
    private JButton executeScriptButton;

    public ScriptPanel() {
        initComponents();
        setVisible(true);
    }

    private JScrollPane getResultArea(){
        var t = new JScrollPane(
                resultTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        t.setPreferredSize(new Dimension(100, 100));
        return t;
    }

    private void initComponents(){
        resultTextArea = new JTextArea();
        executeScriptButton = new JButton();
        scriptChooser = new JFileChooser();

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(getResultArea());

        var buttonsPanel = new Panel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(executeScriptButton);
        add(buttonsPanel);

        scriptChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".zb");
            }

            @Override
            public String getDescription() {
                return getResources().get("MainFrame.scriptChooseText.description");
            }
        });

        executeScriptButton.addActionListener((e) ->{
            scriptChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            scriptChooser.showDialog(this, getResources().get("MainFrame.scriptChooseText.text"));
        });

        scriptChooser.addActionListener((e)->{
            if (scriptChooser.getSelectedFile() == null) return;
            else {
                var scriptPath = scriptChooser.getSelectedFile().getPath();
                resultTextArea.setText(scriptPath);
                invokeLater(() -> executeScript(scriptPath));
            }
        });

        initComponentsI18n();
    }

    private void executeScript(String path){

        var descriptionHolder = new UserAPICommandsDescriptionHolder();

        var uiController = new CLIController(
                descriptionHolder,
                new ConsolePrinter(),
                System.in,
                true);
        UIProvider.setInstance(uiController);
        uiController.display("Hay");

        var invoker = new UICommandInvoker(uiController);
        var scriptExecutor = new ScriptExecutor(
                descriptionHolder,
                invoker
        );

        try {
            scriptExecutor.process(path);
        } catch (FileAccessException e) {
            JOptionPane.showConfirmDialog(
                    this,
                    e.getMessage(),
                    UIResources.getInstance().get("FileAccessException.title.text"),
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    @Override
    protected void initComponentsI18n() {
        executeScriptButton.setText(UIResources.getInstance().get("ScriptPanel.executeScriptButton.text"));
    }
}
