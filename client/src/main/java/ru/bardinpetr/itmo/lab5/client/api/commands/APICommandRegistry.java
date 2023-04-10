package ru.bardinpetr.itmo.lab5.client.api.commands;

import ru.bardinpetr.itmo.lab5.models.commands.api.*;
import ru.bardinpetr.itmo.lab5.models.commands.requests.UserAPICommand;

import java.util.HashMap;
import java.util.List;

/**
 * Class for storing information on available APICommand
 */
public class APICommandRegistry {
    public static final List<UserAPICommand> cmdList = List.of(
            new AddCommand(),
            new AddIfMaxCommand(),
            new AddIfMinCommand(),
            new ClearCommand(),
            new FilterLessPosCommand(),
            new InfoCommand(),
            new PrintDescendingCommand(),
            new UniqueOrganisationCommand(),
            new RemoveByIdCommand(),
            new RemoveGreaterCommand(),
            new ShowCommand()
    );

    private static final HashMap<String, UserAPICommand> map = new HashMap<>();

    static {
        cmdList.forEach(cmd -> map.put(cmd.getType(), cmd));
    }

    private APICommandRegistry() {
    }

    /**
     * Get API command object for name
     *
     * @param name command name
     * @return command object
     */
    public static UserAPICommand getCommand(String name) {
        return map.get(name);
    }
}
