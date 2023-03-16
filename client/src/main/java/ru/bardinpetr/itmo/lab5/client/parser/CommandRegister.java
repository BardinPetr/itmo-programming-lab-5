package ru.bardinpetr.itmo.lab5.client.parser;

import ru.bardinpetr.itmo.lab5.client.tui.View;
import ru.bardinpetr.itmo.lab5.common.serdes.ObjectMapperFactory;
import ru.bardinpetr.itmo.lab5.models.commands.*;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Class for commands registration
 */
public class CommandRegister {
    private static final List<APICommand> cmdList = List.of(
            new AddCommand(),
            new AddIfMaxCommand(),
            new AddIfMinCommand(),
            new ClearCommand(),
            new LocalExecuteScriptCommand(),
            new ExitCommand(),
            new FilterLessPosCommand(),
            new HelpCommand(),
            new InfoCommand(),
            new PrintDescendingCommand(),
            new UniqueOrganisationCommand(),
            new RemoveByIdCommand(),
            new RemoveGreaterCommand(),
            new SaveCommand(),
            new ShowCommand(),
            new UpdateCommand()
    );

    private static final HashMap<String, APICommand> map = new HashMap<>();

    private CommandRegister() {
    }

    static {
        cmdList.forEach(cmd -> map.put(cmd.getType(), cmd));
    }

    public static APICommand getCommand(String name) {
        return map.get(name);
    }

    /**
     * Method for getting oarser with registered commands
     *
     * @param scanner scanner for oarser
     * @param viewer  viewer for parser
     * @return command parser
     */
    public static CommandParser getParser(Scanner scanner, View viewer, Runnable callback) {
        return new CommandParser(map, ObjectMapperFactory.createMapper(), scanner, viewer, callback);
    }
}
