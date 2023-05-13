package ru.bardinpetr.itmo.lab5.mainclient;

import ru.bardinpetr.itmo.lab5.client.api.auth.AuthenticatedConnectorDecorator;
import ru.bardinpetr.itmo.lab5.client.api.auth.impl.RAMCredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.api.connectors.net.UDPAPIClientFactory;
import ru.bardinpetr.itmo.lab5.client.ui.ClientConsoleArgumentsParser;
import ru.bardinpetr.itmo.lab5.client.ui.cli.CLIController;
import ru.bardinpetr.itmo.lab5.client.ui.cli.Interpreter;
import ru.bardinpetr.itmo.lab5.client.ui.cli.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.ui.cli.UICommandInvoker;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.mainclient.api.commands.UserAPICommandRegistry;
import ru.bardinpetr.itmo.lab5.mainclient.api.commands.UserAPICommandsDescriptionHolder;
import ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands.auth.LoginPage;
import ru.bardinpetr.itmo.lab5.mainclient.local.controller.registry.MainClientCommandRegistry;
import ru.bardinpetr.itmo.lab5.mainclient.local.ui.texts.MainTexts;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.DefaultAPICommandAuthenticator;

public class Main {
    public static void main(String[] args) {
        var argParse = new ClientConsoleArgumentsParser(args);

        var apiCredStorage = new RAMCredentialsStorage<DefaultAuthenticationCredentials>();
        var baseAPI = new UDPAPIClientFactory(argParse.getServerFullAddr()).create();
        var authedAPI = new AuthenticatedConnectorDecorator<>(
                DefaultAPICommandAuthenticator.getInstance(),
                apiCredStorage,
                baseAPI
        );
        APIProvider.setConnector(authedAPI);

        var descriptionHolder = new UserAPICommandsDescriptionHolder();

        var uiController = new CLIController(
                descriptionHolder,
                new ConsolePrinter(),
                System.in,
                true);
        uiController.display(MainTexts.get(MainTexts.TextKeys.GREEETING));

        var invoker = new UICommandInvoker(uiController);
        var scriptExecutor = new ScriptExecutor(
                descriptionHolder,
                invoker
        );

        var apiRegistry = new UserAPICommandRegistry();
        var registry = new MainClientCommandRegistry(authedAPI, scriptExecutor, uiController, apiRegistry, invoker, apiCredStorage);

        var interpreter = new Interpreter(registry, uiController, invoker);

        var loginPage = new LoginPage(
                authedAPI, uiController, apiCredStorage,
                interpreter::run
        );
        loginPage.run();
//        interpreter.run();
    }
}
