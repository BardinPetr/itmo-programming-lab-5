package ru.bardinpetr.itmo.lab5.mainclient.local.controller.registry;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.api.commands.APICommandRegistry;
import ru.bardinpetr.itmo.lab5.client.controller.commands.ExitLocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.common.GeneralAPIUILocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.registry.CommandRegistry;
import ru.bardinpetr.itmo.lab5.client.ui.cli.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands.HelpLocalCommand;
import ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands.ScriptLocalCommand;
import ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands.UpdateLocalCommand;

public class MainClientCommandRegistry extends CommandRegistry {
    private final APICommandRegistry registry;

    public MainClientCommandRegistry(APIClientReceiver api, ScriptExecutor scriptExecutor, UIReceiver ui, APICommandRegistry registry) {
        super(api, scriptExecutor);

        this.registry = registry;

        register(new ExitLocalCommand(ui));
        register(new HelpLocalCommand(ui));
        register(new UpdateLocalCommand(api, ui, registry));
        register(new ScriptLocalCommand(api, ui, scriptExecutor, registry));
        registerFromAPI(
                registry.getCommands(),
                new GeneralAPIUILocalCommand(api, ui, registry)
        );
    }

    @Override
    public MainClientCommandRegistry withUI(UIReceiver otherUI) {
        return new MainClientCommandRegistry(api, scriptExecutor, otherUI, registry);
    }
}
