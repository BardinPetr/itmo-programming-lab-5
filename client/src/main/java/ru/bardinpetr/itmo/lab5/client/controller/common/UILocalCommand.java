package ru.bardinpetr.itmo.lab5.client.controller.common;

import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.common.serdes.ValueDeserializer;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class UILocalCommand extends AbstractLocalCommand {
    public static final String NAME_ARG = "cmdName";
    protected final UIReceiver uiReceiver;
    private final ValueDeserializer valueDes;

    public UILocalCommand(UIReceiver receiver) {
        valueDes = new ValueDeserializer();
        uiReceiver = receiver;
    }


    public abstract String getExternalName();

    public List<Field> getCommandInlineArgs(String cmdName) {
        return List.of();
    }

    protected List<Field> getFullInlineArgs(String cmdName) {
        var data = new ArrayList<Field>();
        data.add(new Field(NAME_ARG, String.class));
        data.addAll(getCommandInlineArgs(cmdName));
        return data;
    }

    public void executeWithArgs(List<String> args) {
        if (args.size() == 0)
            throw new RuntimeException("Not command name");

        var defs = getFullInlineArgs(args.get(0));

        if (defs.size() != args.size())
            throw new RuntimeException("Not enough args");

        var objectMap = new HashMap<String, Object>();
        try {
            for (int i = 0; i < args.size(); i++) {
                // TODO: handle exceptions & retries
                objectMap.put(
                        defs.get(i).getName(),
                        valueDes.deserialize(defs.get(i).getValueClass(), args.get(i))
                );
            }
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Command arguments is of invalid type");
        }

        execute((String) objectMap.get(NAME_ARG), objectMap);
    }

    protected void outputResponse(CommandResponse result) {
        // TODO: Add payload print
        if (result.isSuccess())
            uiReceiver.ok();
        else
            uiReceiver.error(result.message());
    }

    @Override
    public CommandResponse execute(String cmdName, Map<String, Object> args) {
        return CommandResponse.ok();
    }
}
