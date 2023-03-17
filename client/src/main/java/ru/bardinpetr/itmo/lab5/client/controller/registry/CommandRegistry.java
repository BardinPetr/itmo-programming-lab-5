package ru.bardinpetr.itmo.lab5.client.controller.registry;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.controller.commands.*;
import ru.bardinpetr.itmo.lab5.client.controller.common.AbstractLocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.decorator.ErrorHandlingCommandDecorator;
import ru.bardinpetr.itmo.lab5.client.parser.APICommandRegistry;
import ru.bardinpetr.itmo.lab5.client.tui.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private static CommandRegistry instance = null;
    private final Map<String, AbstractLocalCommand> commandMap = new HashMap<>();

    private CommandRegistry(APIClientReceiver api, UIReceiver ui) {
        register(new ExitLocalCommand(ui));
        register(new HelpLocalCommand(ui));
        register(new UpdateAPILocalCommand(api, ui));
        register(new ScriptAPILocalCommand(api, ui));
        registerFromAPI(
                APICommandRegistry.cmdList,
                new GeneralAPILocalCommand(api, ui)
        );

    }

    public static CommandRegistry getInstance(APIClientReceiver api, UIReceiver ui) {
        if (instance == null)
            instance = new CommandRegistry(api, ui);
        return instance;
    }

    /**
     * Register command for all name taken from APICommand set
     *
     * @param apiCommands APICommand collection to take names from
     * @param command     target command
     */
    private void registerFromAPI(Collection<APICommand> apiCommands, AbstractLocalCommand command) {
        apiCommands.forEach(i -> register(i.getType(), command));
    }

    /**
     * Register command by its name
     *
     * @param command target command
     */
    private void register(UILocalCommand command) {
        register(command.getExternalName(), command);
    }

    /**
     * Map command to its name
     *
     * @param name    user input command name
     * @param command command object
     */
    private void register(String name, AbstractLocalCommand command) {
        commandMap.put(name, command);
    }

    /**
     * Maps command object to a set of names
     *
     * @param names   collection on command names
     * @param command command object
     */
    private void register(Collection<String> names, AbstractLocalCommand command) {
        names.forEach(i -> register(i, command));
    }

    /**
     * Retrieve command preconfigured object for name
     *
     * @param name user input command name
     * @return command
     */
    public AbstractLocalCommand getCommand(String name) {
        return commandMap.get(name);
    }

    /**
     * Decorate command for proper try-catch error handling
     *
     * @param command target command
     * @return ErrorHandlingCommandDecorator
     */
    private AbstractLocalCommand decorateErrorHandling(AbstractLocalCommand command) {
        return new ErrorHandlingCommandDecorator(command);
    }
}
