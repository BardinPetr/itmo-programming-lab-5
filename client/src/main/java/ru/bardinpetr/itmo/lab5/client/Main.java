package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.api.commands.DescriptionHolder;
import ru.bardinpetr.itmo.lab5.client.api.connectors.NetworkServerConnector;
import ru.bardinpetr.itmo.lab5.client.controller.registry.CommandRegistry;
import ru.bardinpetr.itmo.lab5.client.ui.ClientConsoleArgumentsParser;
import ru.bardinpetr.itmo.lab5.client.ui.cli.CLIController;
import ru.bardinpetr.itmo.lab5.client.ui.cli.Interpreter;
import ru.bardinpetr.itmo.lab5.client.ui.cli.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.ui.cli.UICommandInvoker;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.network.client.impl.SocketAPIClient;
import ru.bardinpetr.itmo.lab5.network.transport.impl.UDPClientTransport;

public class Main {
    public static void main(String[] args) {
        var argParse = new ClientConsoleArgumentsParser(args);

        var connector = new NetworkServerConnector(
                new SocketAPIClient(new UDPClientTransport(argParse.getServerFullAddr()))
        );

        var descriptionHolder = new DescriptionHolder(new Class[]{
                Worker.class
        });

        var ui = new CLIController(
                descriptionHolder,
                new ConsolePrinter(),
                System.in,
                true);

        var invoker = new UICommandInvoker(ui);
        var se = new ScriptExecutor(
                descriptionHolder,
                invoker
        );

        var registry = new CommandRegistry(connector, ui, se);

        var interpreter = new Interpreter(registry, ui, invoker);
        interpreter.run();
    }
}
