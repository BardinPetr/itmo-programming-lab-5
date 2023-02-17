package ru.bardinpetr.itmo.lab5.client.parser;

import ru.bardinpetr.itmo.lab5.models.commands.*;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

import java.util.HashMap;

public class CommandRegister {
    private final Command[] cmdList = new Command[]{
            new AddCommand(),
            new AddIfMaxCommand(),
            new AddIfMinCommand(),
            new ClearCommand(),
            new ExecuteScriptCommand(),
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

    public HashMap<String, Command> getMap() {
        return map;
    }

    private void addCommand(Command cmd) {
        map.put(cmd.getType(), cmd);
    }

    public CommandParser regist() {
        for (Command i : cmdList) {
            addCommand(i);
        }
        return new CommandParser(map);
    }
}
