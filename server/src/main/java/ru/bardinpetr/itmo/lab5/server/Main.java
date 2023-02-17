package ru.bardinpetr.itmo.lab5.server;

import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;

public class Main {
    public static void main(String[] args) throws FileAccessException {
        var executor = new MainExecutor(new FileIOController("db.xml"));

        /*
        var w1 = new Worker(
            "1", new Coordinates(234, 235), 213F, Date.from(Instant.now()), new Organization("asd", OrganizationType.COMMERCIAL), LocalDateTime.now(), Position.CLEANER
        );
        var ex = new Executor();
        ex.registerExecutor(daoExecutor);
        var res = ex.execute(new ExecuteScriptCommand(
                List.of(
                        new ShowCommand(),
                        new ClearCommand(),
                        new AddCommand(w1),
                        new ShowCommand()
                )
        )).getPayload();
        ((ExecuteScriptCommand.ExecuteScriptCommandResponse) res)
                .getResult()
                .stream()
                .map(Response::getPayload)
                .forEach(System.out::println);
         */
    }
}
