package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.logic.UIExecutor;
import ru.bardinpetr.itmo.lab5.client.tui.CommandIOController;
import ru.bardinpetr.itmo.lab5.client.tui.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.tui.View;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.MainExecutor;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;

public class Main {
    public static void main(String[] args) throws FileAccessException {
        var db = new FileDBController<>(
                new FileIOController("db.xml"),
                WorkerCollection.class
        );
        var serverExecutor = new MainExecutor(db);
        Executor executor = new Executor();
        executor.registerExecutor(new UIExecutor());
        executor.registerExecutor(serverExecutor);

        View view = new ConsolePrinter();

        var cmdController = new CommandIOController(
                view,
                System.in,
                executor::execute
        );
        cmdController.run();


    }
}
