package ru.bardinpetr.itmo.lab5.client.controller.common;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.parser.APICommandRegistry;
import ru.bardinpetr.itmo.lab5.client.tui.cli.UIReceiver;
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

    protected APICommand retriveAPICommand(String name) {
        var base = APICommandRegistry.getCommand(name);
        if (base == null)
            throw new RuntimeException("No such command");
        return base;
    }

    @Override
    public List<Field> getCommandInlineArgs(String cmdName) {
        return List.of(retriveAPICommand(cmdName).getInlineArgs());
    }

    protected APICommand prepareAPIMessage(String name, Map<String, Object> args) {
        var base = retriveAPICommand(name);

        var objectMap = new HashMap<>(args);
        for (Field i : base.getInteractArgs()) {
            try {
                objectMap.put(i.getName(), uiReceiver.fill(i.getValueClass()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return mapper.convertValue(objectMap, base.getClass());
    }

    @Override
    public CommandResponse execute(String cmdName, Map<String, Object> args) {
        var cmd = prepareAPIMessage(cmdName, args);
        var serverResp = apiClientReceiver.call(cmd);
        // TODO: insert payload to CommandResponse
        var res = new CommandResponse(serverResp.isSuccess(), serverResp.getText());
        outputResponse(res);
        return null;
    }
}
