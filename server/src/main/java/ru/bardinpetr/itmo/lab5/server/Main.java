package ru.bardinpetr.itmo.lab5.server;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;
import ru.bardinpetr.itmo.lab5.network.app.special.impl.ErrorHandlingApplication;
import ru.bardinpetr.itmo.lab5.network.app.special.impl.UDPInputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.app.special.impl.UDPOutputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.transport.impl.UDPServerTransport;
import ru.bardinpetr.itmo.lab5.server.api.ExecutorAdapterApplication;
import ru.bardinpetr.itmo.lab5.server.executor.DBExecutor;
import ru.bardinpetr.itmo.lab5.server.ui.ServerConsoleArgumentsParser;

@Slf4j
public class Main {
    public static void main(String[] args) {
        SetupJUL.loadProperties(Main.class);

        var argParse = new ServerConsoleArgumentsParser(args);

        var dbExecutor = new DBExecutor(argParse.getDatabasePath());
        var dbApp = new ExecutorAdapterApplication(dbExecutor);

        var transport = new UDPServerTransport();
        var responderApp = new UDPOutputTransportApplication(transport);
        var mainApp = new UDPInputTransportApplication(transport);
        var errorApp = new ErrorHandlingApplication();

        mainApp.use(responderApp);
        mainApp.use(dbApp);
        mainApp.use(errorApp);

        mainApp.start();
    }
}
