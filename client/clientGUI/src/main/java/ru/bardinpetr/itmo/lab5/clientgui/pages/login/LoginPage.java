package ru.bardinpetr.itmo.lab5.clientgui.pages.login;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.auth.ICredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.pages.common.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.auth.AuthCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.PasswordLoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.RegisterCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginPage extends ResourcedFrame {
    private final ICredentialsStorage<StoredJWTCredentials> credentialsStorage;
    private final APIClientConnector apiConnector;
    private final Runnable onSuccess;

    private JButton loginButton;
    private JButton registerButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showCheckBox;

    public LoginPage(Runnable onSuccess) {
        super("LoginPage");
        this.onSuccess = onSuccess;

        apiConnector = APIProvider.getConnector();
        credentialsStorage = APIProvider.getCredentialsStorage();

        if (credentialsStorage.getCredentials() != null) {
            onSuccess.run();
            return;
        }

        build();

        var cur = new AtomicInteger();
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(
                () -> getUIResources().setLocale(cur.getAndIncrement() % 2 == 0 ? Locale.US : Locale.forLanguageTag("ru-RU")),
                0, 1, TimeUnit.SECONDS
        );
    }

    public void build() {
        var mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        loginButton = new JButton();
        loginButton.addActionListener(this::onButtonClick);
        registerButton = new JButton();
        registerButton.addActionListener(this::onButtonClick);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');

        showCheckBox = new JCheckBox("Show pass");
        showCheckBox.addItemListener(this::togglePasswordVisibility);

        mainPanel.add(new JLabel("Login"));
        mainPanel.add(new JLabel("Username"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password"));
        mainPanel.add(passwordField);
        mainPanel.add(showCheckBox);
        mainPanel.add(loginButton);
        mainPanel.add(registerButton);

        loadResources(getResources());

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    protected void loadResources(ResourceBundle resources) {
        loginButton.setText(resources.getString("login_btn"));
        registerButton.setText(resources.getString("register_btn"));
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
        var validation = cmd.validate();
        if (!validation.isAllowed()) {
            new JDialog(this, validation.getMsg());
        }

        SwingUtilities.invokeLater(() -> sendCommand(cmd));
    }

    private void sendCommand(APICommand cmd) {
        APICommandResponse result;
        try {
            result = apiConnector.call(cmd);
        } catch (APIClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Request failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!result.isSuccess()) {
            JOptionPane.showMessageDialog(this, result.getUserMessage(), "Authentication failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        var loginResponse = ((AuthCommand.AuthCommandResponse) result).getData();
        credentialsStorage.setCredentials(new StoredJWTCredentials((JWTLoginResponse) loginResponse));

        onSuccess.run();
        dispose();
    }

}
