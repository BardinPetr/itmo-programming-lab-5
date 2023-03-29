package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.api.commands.DescriptionHolder;
import ru.bardinpetr.itmo.lab5.client.api.connectors.LocalExecutorAPIConnector;
import ru.bardinpetr.itmo.lab5.client.controller.registry.CommandRegistry;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.ui.cli.CLIController;
import ru.bardinpetr.itmo.lab5.client.ui.cli.Interpreter;
import ru.bardinpetr.itmo.lab5.client.ui.cli.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.ui.cli.UICommandInvoker;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.server.MainExecutor;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println(RussianText.get(TextKeys.INVALID_APP_ARGUMENTS));
            System.exit(1);
            return;
        }

        var serverExecutor = new MainExecutor(Path.of(args[0]));
        var api = new LocalExecutorAPIConnector(serverExecutor);

        DescriptionHolder descriptionHolder = new DescriptionHolder(new Class[]{
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

        var registry = new CommandRegistry(api, ui, se);

        var interpreter = new Interpreter(registry, ui, invoker);
        interpreter.run();
    }
}
