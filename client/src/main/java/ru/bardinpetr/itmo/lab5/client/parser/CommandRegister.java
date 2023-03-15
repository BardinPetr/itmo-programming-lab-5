package ru.bardinpetr.itmo.lab5.client.parser;

import ru.bardinpetr.itmo.lab5.client.tui.Printer;
import ru.bardinpetr.itmo.lab5.common.serdes.ObjectMapperFactory;
import ru.bardinpetr.itmo.lab5.models.commands.*;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Class for commands registration
 */
@Deprecated
public class CommandRegister {
    private final Command[] cmdList = new Command[]{
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
    };
    private final HashMap<String, Command> map = new HashMap<>();

    public CommandRegister() {
        for (Command i : cmdList) {
            addCommand(i);
        }
    }

    private void addCommand(Command cmd) {
        map.put(cmd.getType(), cmd);
    }

    /**
     * Method for getting oarser with registered commands
     *
     * @param scanner scanner for oarser
     * @param viewer  viewer for parser
     * @return command parser
     */
    public CommandParser getParser(Scanner scanner, Printer viewer, Runnable callback) {
        return new CommandParser(map, ObjectMapperFactory.createMapper(), scanner, viewer, callback);
    }
}
