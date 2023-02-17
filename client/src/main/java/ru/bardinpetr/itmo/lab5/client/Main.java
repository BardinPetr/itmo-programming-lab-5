package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.logic.UIExecutor;
import ru.bardinpetr.itmo.lab5.client.parser.CommandRegister;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.tui.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.tui.View;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.server.MainExecutor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileAccessException {
        if (args.length != 1) {
            System.err.println("Please call this program with single argument - path to database file");
            System.exit(1);
        }
        var serverExecutor = new MainExecutor(new FileIOController(args[0]));

        Scanner scanner = new Scanner(System.in);
        View view = new ConsolePrinter();
        CommandRegister cmdRegister = new CommandRegister();
        var cmdParser = cmdRegister.regist();

        Executor executor = new Executor();
        executor.registerExecutor(new UIExecutor());
        executor.registerExecutor(serverExecutor);

        while (true) {
            try {
                view.showLine("> ");
                Command cmd = cmdParser.parse(scanner.next());
                var resp = executor.execute(cmd);
                if (resp.isSuccess()) {
                    ICommandResponse payload = resp.getPayload();
                    view.show(resp.getPayload() == null ? "OK!" : payload.getUserMessage());
                } else {
                    view.show("Error: " + resp.getText());
                }
            } catch (ParserException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
