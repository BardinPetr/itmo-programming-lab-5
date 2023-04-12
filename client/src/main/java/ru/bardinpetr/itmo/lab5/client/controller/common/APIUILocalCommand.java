package ru.bardinpetr.itmo.lab5.client.controller.common;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.api.commands.APICommandRegistry;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.common.serdes.ObjectMapperFactory;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.UserAPICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class for all local commands that have API call as a result
 */
public abstract class APIUILocalCommand extends UILocalCommand {

    protected final APIClientReceiver apiClientReceiver;
    private final ObjectMapper mapper;

    public APIUILocalCommand(APIClientReceiver api, UIReceiver ui) {
        super(ui);
        this.apiClientReceiver = api;
        this.mapper = ObjectMapperFactory.createMapper();
    }

    /**
     * Get api message object by command name from underling APICommand
     *
     * @param name api command name
     * @return api message object
     */
    protected UserAPICommand retrieveAPICommand(String name) {
        var base = APICommandRegistry.getCommand(name);
        if (base == null)
            throw new RuntimeException("No such command");
        return base;
    }

    /**
     * Get inline args from underling APICommand
     *
     * @param cmdName command name if command realization depends
     */
    @Override
    public List<Field<?>> getCommandInlineArgs(String cmdName) {
        return List.of(retrieveAPICommand(cmdName).getInlineArgs());
    }

    /**
     * Build APICommand object from "inline arguments" and ask via UI for "interact args"
     *
     * @param name command name
     * @param args arguments
     * @return build APICommand object filled with request data
     */
    protected APICommand prepareAPIMessage(String name, Map<String, Object> args) {
        var base = retrieveAPICommand(name);

        var objectMap = new HashMap<>(args);
        for (var i : base.getInteractArgs())
            objectMap.put(i.getName(), uiReceiver.fill(i.getValueClass()));

        return mapper.convertValue(objectMap, base.getClass());
    }

    /**
     * Build APICommand with prepareAPIMessage and call server
     *
     * @param cmdName command handler/name - used for commands with multiple realizations
     * @param args    arguments of command parsed according to fields
     * @return APICommand response as CommandResponse
     */
    @Override
    public CommandResponse<APICommandResponse> execute(String cmdName, Map<String, Object> args) {
        var cmd = prepareAPIMessage(cmdName, args);
        if (cmd == null)
            throw new RuntimeException("Command was not build properly");
        APICommandResponse serverResp = null;
        try {
            serverResp = apiClientReceiver.call(cmd);
        } catch (APIClientException e) {
            // TODO proper handling of api errors
            throw new RuntimeException(e);
        }
        return new CommandResponse<>(serverResp.isSuccess(), serverResp.getTextualResponse(), serverResp);
    }
}
