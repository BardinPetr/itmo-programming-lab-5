package ru.bardinpetr.itmo.lab5.client.parser;

import ru.bardinpetr.itmo.lab5.client.tui.cli.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.common.serdes.ObjectMapperFactory;
import ru.bardinpetr.itmo.lab5.models.commands.*;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Class for commands registration
 */
public class APICommandRegistry {
    public static final List<APICommand> cmdList = List.of(
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
            new SaveCommand(),
            new ShowCommand()
    );

    private static final HashMap<String, APICommand> map = new HashMap<>();

    private APICommandRegistry() {
    }

    static {
        cmdList.forEach(cmd -> map.put(cmd.getType(), cmd));
    }

    /**
     * Get API command object for name
     *
     * @param name command name
     * @return command object
     */
    public static APICommand getCommand(String name) {
        return map.get(name);
    }

    /**
     * Method for getting oarser with registered commands
     *
     * @param scanner scanner for oarser
     * @param printer printer for parser
     * @return command parser
     */
    public static CommandParser getParser(Scanner scanner, ConsolePrinter printer, Runnable callback) {
        return new CommandParser(map, ObjectMapperFactory.createMapper(), scanner, printer, callback);
    }
}
