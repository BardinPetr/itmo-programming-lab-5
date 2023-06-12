package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.login;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.auth.ICredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.PasswordField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.UsernameField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.lang.LanguageChanger;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.commands.auth.AuthCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.PasswordLoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.RegisterCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

public class LoginPage extends ResourcedFrame {
    private final ICredentialsStorage<StoredJWTCredentials> credentialsStorage;
    private final APIClientConnector apiConnector;
    private final Runnable onSuccess;
    private final JLabel userLabel = new JLabel();
    private final JLabel passLabel = new JLabel();
    private JButton loginButton;
    private JButton registerButton;
    private UsernameField usernameField;
    private PasswordField passwordField;
    private JCheckBox showCheckBox;


    public LoginPage(Runnable onSuccess) {
        build();
        this.onSuccess = onSuccess;

        apiConnector = APIProvider.getConnector();
        credentialsStorage = APIProvider.getCredentialsStorage();

        if (credentialsStorage.getCredentials() != null) {
            onSuccess.run();
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

        usernameField = new UsernameField((e) -> {
        });
        passwordField = new PasswordField((e) -> {
        });
        passwordField.setEchoChar('*');

        showCheckBox = new JCheckBox("Show pass");
        showCheckBox.addItemListener(this::togglePasswordVisibility);


        mainPanel.add(userLabel, GridConstrains.placedAdd(0, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE));
        mainPanel.add(usernameField, GridConstrains.placedAdd(0, 2, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL));
        mainPanel.add(passLabel, GridConstrains.placedAdd(0, 3, GridBagConstraints.CENTER, GridBagConstraints.NONE));
        var passPanel = new JPanel();
        passPanel.setLayout(new BoxLayout(passPanel, BoxLayout.X_AXIS));
        passPanel.add(passwordField);
        passPanel.add(showCheckBox);
        mainPanel.add(passPanel, GridConstrains.placedAdd(0, 4, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL));
        var buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(loginButton, GridConstrains.placedAdd(0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL));
        buttonPanel.add(registerButton, GridConstrains.placedAdd(1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL));
        buttonPanel.add(new LanguageChanger(), GridConstrains.placedAdd(1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE));
        mainPanel.add(buttonPanel, GridConstrains.placedAdd(0, 5, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL));
        initComponentsI18n();

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    protected void initComponentsI18n() {
        var resources = getResources();
        setTitle(getResources().get("loginPage.title"));
        loginButton.setText(resources.get("loginPage.loginButton.text"));
        registerButton.setText(resources.get("loginPage.registerButton.text"));
        userLabel.setText(resources.get("loginPage.label2.text"));
        passLabel.setText(resources.get("loginPage.label3.text"));
    }

    private void togglePasswordVisibility(ItemEvent e) {
        var show = e.getStateChange() == ItemEvent.SELECTED;
        passwordField.setEchoChar(show ? '\u0000' : '*');
    }

    private void onButtonClick(ActionEvent e) {
        var username = usernameField.getData();
        var password = passwordField.getData();
        if (!username.isAllowed) {
            JOptionPane.showMessageDialog(
                    this,
                    getResources().get(username.msg),
                    getResources().get("CredentialsValidator.invalid.text"),
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }
        if (!password.isAllowed) {
            JOptionPane.showMessageDialog(
                    this,
                    getResources().get(password.msg),
                    getResources().get("CredentialsValidator.invalid.text"),
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        AuthCommand<DefaultAuthenticationCredentials> cmd;
        if (e.getSource() == loginButton)
            cmd = new PasswordLoginCommand();
        else
            cmd = new RegisterCommand();

        cmd.setCredentials(new DefaultAuthenticationCredentials(username.data, password.data));

        new APICommandMenger().sendCommand(
                cmd,
                this,
                "loginPage.error.authorizationFailed",
                (result) -> {
                    var loginResponse = ((AuthCommand.AuthCommandResponse) result).getData();
                    credentialsStorage.setCredentials(new StoredJWTCredentials((JWTLoginResponse) loginResponse));
                    onSuccess.run();
                    dispose();
                }
        );

    }

}
