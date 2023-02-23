package ru.bardinpetr.itmo.lab5.client.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.tui.View;
import ru.bardinpetr.itmo.lab5.models.commands.*;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

import java.util.HashMap;
import java.util.Scanner;

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

    public HashMap<String, Command> getMap() {
        return map;
    }

    private void addCommand(Command cmd) {
        map.put(cmd.getType(), cmd);
    }

    public CommandParser getParser(ObjectMapper mapper, Scanner scanner, View viewer) {
        return new CommandParser(map, mapper, scanner, viewer);
    }
}
