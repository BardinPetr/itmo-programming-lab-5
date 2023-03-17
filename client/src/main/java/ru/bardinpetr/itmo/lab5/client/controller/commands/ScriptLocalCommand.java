package ru.bardinpetr.itmo.lab5.client.controller.commands;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.controller.common.APILocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.common.CommandResponse;
import ru.bardinpetr.itmo.lab5.client.tui.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.tui.cli.UIReceiver;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

import java.util.Map;


public class ScriptLocalCommand extends APILocalCommand {
    private ScriptExecutor se;

    public ScriptLocalCommand(APIClientReceiver api, UIReceiver ui, ScriptExecutor se) {
        super(api, ui);
        this.se = se;
    }

    @Override
    protected APICommand retrieveAPICommand(String name) {
        return null;
    }

    @Override
    protected APICommand prepareAPIMessage(String name, Map<String, Object> args) {
        return null;
    }

    @Override
    public String getExternalName() {
        return "execute_script";
    }

    @Override
    public CommandResponse execute(String cmdName, Map<String, Object> args) {
        var path = args.get("fileName");
        if (path == null)
            throw new RuntimeException("No script file passed");


        try {
            se.process((String) path);
        } catch (FileAccessException e) {
            throw new RuntimeException("Can't get access to script");
        }

        return null;
    }
}
