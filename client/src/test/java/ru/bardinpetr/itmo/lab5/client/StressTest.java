package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.api.connectors.UDPAPIClientFactory;
import ru.bardinpetr.itmo.lab5.client.ui.ClientConsoleArgumentsParser;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.ClearCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetWorkerIdsCommand;
import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.Executors;

public class StressTest {

    public static void main(String[] args) {
        var argParse = new ClientConsoleArgumentsParser(args);

        var executor = Executors.newFixedThreadPool(16);

        var apiConnector =
                new UDPAPIClientFactory(argParse.getServerFullAddr())
                        .create();

        try {
            apiConnector.call(new ClearCommand());
        } catch (APIClientException e) {
        }
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    var cur = new UDPAPIClientFactory(argParse.getServerFullAddr())
                            .create();
                    cur
                            .call(new AddCommand(new Worker(
                                    0,
                                    ZonedDateTime.now(),
                                    "test%d".formatted(finalI),
                                    234,
                                    Date.from(Instant.now()),
                                    null,
                                    new Coordinates(123, 123),
                                    null,
                                    null
                            )));
                    System.out.println(cur.call(new ClearCommand()));
//                    System.out.println(((InfoCommand.InfoCommandResponse) cur.call(new ClearCommand())).getResult().getItemsCount());
                } catch (APIClientException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        try {
            System.out.println("END");
            var res = apiConnector.call(new GetWorkerIdsCommand());
            System.out.println(((GetWorkerIdsCommand.GetWorkerIdsCommandResponse) res).getResult().size());
        } catch (APIClientException e) {
        }
    }
}
