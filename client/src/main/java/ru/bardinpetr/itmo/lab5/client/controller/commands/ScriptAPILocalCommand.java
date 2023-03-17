package ru.bardinpetr.itmo.lab5.client.controller.commands;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.controller.common.APILocalCommand;
import ru.bardinpetr.itmo.lab5.client.tui.newThings.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.ExecuteScriptCommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

import java.util.List;
import java.util.Map;


public class ScriptAPILocalCommand extends APILocalCommand {

    public ScriptAPILocalCommand(APIClientReceiver api, UIReceiver ui) {
        super(api, ui);
    }

    @Override
    public String getExternalName() {
        return "execute_script";
    }

    @Override
    protected APICommand retriveAPICommand(String name) {
        return new ExecuteScriptCommand();
    }

    @Override
    protected APICommand prepareAPIMessage(String name, Map<String, Object> args) {
        var cmd = new ExecuteScriptCommand();
        var path = args.get("fileName");
        if (path == null)
            throw new RuntimeException("No script file passed");
        // TODO parse script
        cmd.setCommands(List.of());
        return cmd;
    }
}
