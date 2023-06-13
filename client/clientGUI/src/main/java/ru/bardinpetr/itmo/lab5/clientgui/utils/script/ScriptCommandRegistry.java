package ru.bardinpetr.itmo.lab5.clientgui.utils.script;

import ru.bardinpetr.itmo.lab5.client.api.commands.APICommandRegistry;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.commands.ExitLocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.common.GeneralAPIUILocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.registry.CommandRegistry;
import ru.bardinpetr.itmo.lab5.client.ui.cli.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands.*;

public class ScriptCommandRegistry extends CommandRegistry {
    private final APICommandRegistry registry;

    public ScriptCommandRegistry(ScriptExecutor scriptExecutor, APICommandRegistry registry, UIReceiver ui) {
        super(
                APIProvider.getConnector(),
                scriptExecutor
        );
        this.registry = registry;
//        register(new ExitLocalCommand(ui));
//        register(new HelpLocalCommand(ui));
        register(new AddLocalCommand(api, ui, registry));
        register(new AddOrgLocalCommand(api, ui, registry));
        register(new DemoAddLocalCommand(api, ui, registry));
        register(new UpdateLocalCommand(api, ui, registry));
        register(new ShowLocalCommand(api, ui, registry));
        register(new DescShowLocalCommand(api, ui, registry));
        register(new ScriptLocalCommand(api, ui, scriptExecutor, registry));
        registerFromAPI(
                registry.getCommands(),
                new GeneralAPIUILocalCommand(api, ui, registry)
        );
    }


    @Override
    public CommandRegistry withUI(UIReceiver otherUI) {
        return new ScriptCommandRegistry(
                scriptExecutor,
                registry,
                otherUI
        );
    }
}
