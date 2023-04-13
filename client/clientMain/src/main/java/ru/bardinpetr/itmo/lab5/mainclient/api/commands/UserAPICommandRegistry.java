package ru.bardinpetr.itmo.lab5.mainclient.api.commands;

import ru.bardinpetr.itmo.lab5.client.api.commands.APICommandRegistry;
import ru.bardinpetr.itmo.lab5.models.commands.api.*;

import java.util.List;

public class UserAPICommandRegistry extends APICommandRegistry {

    public UserAPICommandRegistry() {
        super(List.of(
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
        ));
    }
}
