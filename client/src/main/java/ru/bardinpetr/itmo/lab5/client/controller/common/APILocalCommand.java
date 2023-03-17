package ru.bardinpetr.itmo.lab5.client.controller.common;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.api.commands.APICommandRegistry;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.common.serdes.ObjectMapperFactory;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class APILocalCommand extends UILocalCommand {

    protected final APIClientReceiver apiClientReceiver;
    private final ObjectMapper mapper;

    public APILocalCommand(APIClientReceiver api, UIReceiver ui) {
        super(ui);
        this.apiClientReceiver = api;
        this.mapper = ObjectMapperFactory.createMapper();
    }

    protected APICommand retrieveAPICommand(String name) {
        var base = APICommandRegistry.getCommand(name);
        if (base == null)
            throw new RuntimeException("No such command");
        return base;
    }

    @Override
    public List<Field> getCommandInlineArgs(String cmdName) {
        return List.of(retrieveAPICommand(cmdName).getInlineArgs());
    }

    protected APICommand prepareAPIMessage(String name, Map<String, Object> args) {
        var base = retrieveAPICommand(name);

        var objectMap = new HashMap<>(args);
        for (var i : base.getInteractArgs())
            objectMap.put(i.getName(), uiReceiver.fill(i.getValueClass()));

        return mapper.convertValue(objectMap, base.getClass());
    }

    @Override
    public CommandResponse execute(String cmdName, Map<String, Object> args) {
        var cmd = prepareAPIMessage(cmdName, args);
        if (cmd == null)
            throw new RuntimeException("Command was not build properly");
        var serverResp = apiClientReceiver.call(cmd);
        return new CommandResponse(serverResp.isSuccess(), serverResp.getText(), serverResp.getPayload());
    }
}
