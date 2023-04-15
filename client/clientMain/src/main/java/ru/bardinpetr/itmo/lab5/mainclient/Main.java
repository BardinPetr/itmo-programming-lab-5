package ru.bardinpetr.itmo.lab5.mainclient;

import ru.bardinpetr.itmo.lab5.client.api.connectors.UDPAPIClientFactory;
import ru.bardinpetr.itmo.lab5.client.ui.ClientConsoleArgumentsParser;
import ru.bardinpetr.itmo.lab5.client.ui.cli.CLIController;
import ru.bardinpetr.itmo.lab5.client.ui.cli.Interpreter;
import ru.bardinpetr.itmo.lab5.client.ui.cli.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.ui.cli.UICommandInvoker;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.mainclient.api.commands.UserAPICommandRegistry;
import ru.bardinpetr.itmo.lab5.mainclient.api.commands.UserAPICommandsDescriptionHolder;
import ru.bardinpetr.itmo.lab5.mainclient.local.controller.registry.MainClientCommandRegistry;
import ru.bardinpetr.itmo.lab5.mainclient.local.ui.texts.MainTexts;

public class Main {
    public static void main(String[] args) {
        var argParse = new ClientConsoleArgumentsParser(args);

        var apiConnector =
                new UDPAPIClientFactory(argParse.getServerFullAddr())
                        .create();

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
        var registry = new MainClientCommandRegistry(apiConnector, scriptExecutor, uiController, apiRegistry, invoker);

        System.out.println("test");

        var interpreter = new Interpreter(registry, uiController, invoker);
        interpreter.run();
    }
}
