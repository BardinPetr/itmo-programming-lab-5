package ru.bardinpetr.itmo.lab5.client.controller.commands;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.controller.common.APILocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.common.CommandResponse;
import ru.bardinpetr.itmo.lab5.client.ui.cli.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

import java.util.Map;


/**
 * Command for processing nested scripts
 */
public class ScriptLocalCommand extends APILocalCommand {
    private final ScriptExecutor scriptExecutor;

    public ScriptLocalCommand(APIClientReceiver api, UIReceiver ui, ScriptExecutor scriptExecutor) {
        super(api, ui);
        this.scriptExecutor = scriptExecutor;
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

    /**
     * Using ScriptExecutor recursively parse script file
     *
     * @param cmdName should be "execute_script"
     * @param args    should contain "fileName" key with script file path
     * @return execution result
     */
    @Override
    public CommandResponse execute(String cmdName, Map<String, Object> args) {
        String path = (String) args.get("fileName");
        if (path == null)
            throw new RuntimeException("No script file passed");

        try {
            scriptExecutor.process(path);
        } catch (FileAccessException e) {
            throw new RuntimeException("Can't get access to script");
        }

        return CommandResponse.ok();
    }
}
