package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.login;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.auth.ICredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.lang.LanguageChanger;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.auth.AuthCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.PasswordLoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.RegisterCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

public class LoginPage extends ResourcedFrame {
    private final ICredentialsStorage<StoredJWTCredentials> credentialsStorage;
    private final APIClientConnector apiConnector;
    private final Runnable onSuccess;

    private JButton loginButton;
    private JButton registerButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showCheckBox;
    private JLabel userLabel = new JLabel();
    private JLabel passLabel = new JLabel();
    private APICommandMenger commandMenger;

    public LoginPage(Runnable onSuccess) {
        build();
        this.onSuccess = onSuccess;

        apiConnector = APIProvider.getConnector();
        credentialsStorage = APIProvider.getCredentialsStorage();

        commandMenger = new APICommandMenger();

        if (credentialsStorage.getCredentials() != null) {
            onSuccess.run();
            return;
        }

    }

    public void build() {
        setPreferredSize(new Dimension(300, 170));
        setResizable(false);
        var mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        loginButton = new JButton();
        loginButton.addActionListener(this::onButtonClick);
        registerButton = new JButton();
        registerButton.addActionListener(this::onButtonClick);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');

        showCheckBox = new JCheckBox("Show pass");
        showCheckBox.addItemListener(this::togglePasswordVisibility);


        mainPanel.add(userLabel, GridConstrains.placedAdd(0,1, GridBagConstraints.CENTER, GridBagConstraints.NONE));
        mainPanel.add(usernameField, GridConstrains.placedAdd(0,2, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL));
        mainPanel.add(passLabel, GridConstrains.placedAdd(0,3, GridBagConstraints.CENTER, GridBagConstraints.NONE));
        var passPanel = new JPanel();
        passPanel.setLayout(new BoxLayout(passPanel, BoxLayout.X_AXIS));
        passPanel.add(passwordField);
        passPanel.add(showCheckBox);
        mainPanel.add(passPanel, GridConstrains.placedAdd(0,4, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL));
        var buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(loginButton, GridConstrains.placedAdd(0,0, GridBagConstraints.CENTER, GridBagConstraints.NONE));
        buttonPanel.add(registerButton, GridConstrains.placedAdd(1,0, GridBagConstraints.CENTER, GridBagConstraints.NONE));
        buttonPanel.add(new LanguageChanger(), GridConstrains.placedAdd(3,0, GridBagConstraints.EAST, GridBagConstraints.NONE));
        mainPanel.add(buttonPanel, GridConstrains.placedAdd(0,5, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL));
        initComponentsI18n();

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    protected void initComponentsI18n() {
        var resources = getResources();
        setTitle(getResources().getString("loginPage.title"));
        loginButton.setText(resources.getString("loginPage.loginButton.text"));
        registerButton.setText(resources.getString("loginPage.registerButton.text"));
        userLabel.setText(resources.getString("loginPage.label2.text"));
        passLabel.setText(resources.getString("loginPage.label3.text"));
    }

    private void togglePasswordVisibility(ItemEvent e) {
        var show = e.getStateChange() == ItemEvent.SELECTED;
        passwordField.setEchoChar(show ? '\u0000' : '*');
    }

    private void onButtonClick(ActionEvent e) {
        var username = usernameField.getText();
        var password = String.valueOf(passwordField.getPassword());

        AuthCommand<DefaultAuthenticationCredentials> cmd;
        if (e.getSource() == loginButton)
            cmd = new PasswordLoginCommand();
        else
            cmd = new RegisterCommand();

        cmd.setCredentials(new DefaultAuthenticationCredentials(username, password));

        SwingUtilities.invokeLater(() -> {
            var validation = cmd.validate();
            if (!validation.isAllowed()) {
                JOptionPane.showMessageDialog(
                        this,
                        getResources().getString(validation.getMsg()),
                        getResources().getString("command.error.invalidField"),
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            APICommandResponse result;
            try {
                result = apiConnector.call(cmd);
            } catch (APIClientException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        getResources().getString(ex.getMessage()),
                        getResources().getString("command.error.requestFailed"),
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (!result.isSuccess()) {
                JOptionPane.showMessageDialog(
                        this,
                        getResources().getString(result.getUserMessage()),
                        getResources().getString("loginPage.error.authorizationFailed"),
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            var loginResponse = ((AuthCommand.AuthCommandResponse) result).getData();
            credentialsStorage.setCredentials(new StoredJWTCredentials((JWTLoginResponse) loginResponse));
            onSuccess.run();
            dispose();
        });
    }

}
