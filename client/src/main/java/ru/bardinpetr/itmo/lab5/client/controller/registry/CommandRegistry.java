package ru.bardinpetr.itmo.lab5.client.controller.registry;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.api.commands.APICommandRegistry;
import ru.bardinpetr.itmo.lab5.client.controller.commands.*;
import ru.bardinpetr.itmo.lab5.client.controller.common.AbstractLocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;
import ru.bardinpetr.itmo.lab5.client.ui.cli.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, AbstractLocalCommand> commandMap = new HashMap<>();
    private final APIClientReceiver api;
    private final ScriptExecutor scriptExecutor;

    public CommandRegistry(APIClientReceiver api, UIReceiver ui, ScriptExecutor scriptExecutor) {
        this.scriptExecutor = scriptExecutor;
        this.api = api;

        scriptExecutor.setRegistry(this);

        register(new ExitLocalCommand(ui));
        register(new HelpLocalCommand(ui));
        register(new UpdateLocalCommand(api, ui));
        register(new ScriptLocalCommand(api, ui, scriptExecutor));
        registerFromAPI(
                APICommandRegistry.cmdList,
                new GeneralAPILocalCommand(api, ui)
        );
    }

    /**
     * Create same command invoker with changed ui dependency
     *
     * @param otherUI ui receiver to be used
     * @return this command registry with all commands and changed ui dependency
     */
    public CommandRegistry withUI(UIReceiver otherUI) {
        return new CommandRegistry(api, otherUI, scriptExecutor);
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
     * Map command to its name.
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
}
