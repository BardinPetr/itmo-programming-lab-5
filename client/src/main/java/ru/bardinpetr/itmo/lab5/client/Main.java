package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.logic.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.logic.UIExecutor;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.tui.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.tui.View;
import ru.bardinpetr.itmo.lab5.client.tui.commands.controller.CLIController;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.server.MainExecutor;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println(RussianText.get(TextKeys.INVALID_APP_ARGUMENTS));
            System.exit(1);
            return;
        }

        FileIOController file;
        try {
            file = new FileIOController(args[0]);
        } catch (FileAccessException e) {
            System.err.printf(RussianText.get(TextKeys.FILEIO_ERROR_MESSAGE_TEMLPATE), e.getMessage());
            System.exit(1);
            return;
        }

        var serverExecutor = new MainExecutor(file);
        Executor executor = new Executor();
        executor.registerExecutor(new ScriptExecutor());
        executor.registerExecutor(new UIExecutor());
        executor.registerExecutor(serverExecutor);

        View view = new ConsolePrinter();
        CLIController cmdController = new CLIController(
                view,
                System.in,
                executor::execute
        );
        cmdController.run();
    }
}
