package ru.bardinpetr.itmo.lab5.server;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.network.app.ApplicationServer;
import ru.bardinpetr.itmo.lab5.server.api.ExecutorAdapter;
import ru.bardinpetr.itmo.lab5.server.ui.ServerConsoleArgumentsParser;

@Slf4j
public class Main {
    public static void main(String[] args) {
        var argParse = new ServerConsoleArgumentsParser(args);

        var dbExecutor = new DBExecutor(argParse.getDatabasePath());
        var server = new ApplicationServer();

        var handler = new ExecutorAdapter(dbExecutor::execute);
        server.registerReplyHandler(handler);

        server.start();
    }
}
