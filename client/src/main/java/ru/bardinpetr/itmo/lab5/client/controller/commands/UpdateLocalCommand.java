package ru.bardinpetr.itmo.lab5.client.controller.commands;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.controller.common.APILocalCommand;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetWorkerCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetWorkerIdsCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.UpdateCommand;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.util.Map;


/**
 * Worker update command
 */
public class UpdateLocalCommand extends APILocalCommand {

    public UpdateLocalCommand(APIClientReceiver api, UIReceiver ui) {
        super(api, ui);
    }

    @Override
    public String getExternalName() {
        return "update";
    }

    @Override
    protected APICommand retrieveAPICommand(String name) {
        return new UpdateCommand();
    }

    /**
     * Builds update command with checks of ID and with use of default values
     *
     * @param name command name
     * @param args arguments
     * @return UpdateCommand
     */
    @Override
    protected APICommand prepareAPIMessage(String name, Map<String, Object> args) {
        var id = (Integer) args.get("id");
        if (id == null)
            throw new RuntimeException("Object id to update not specified");

        var availableIdsResp = apiClientReceiver.call(new GetWorkerIdsCommand());
        if (!availableIdsResp.isSuccess())
            throw new RuntimeException("Could not retrieve existing data");

        var ids = ((GetWorkerIdsCommand.GetWorkerIdsCommandResponse) availableIdsResp.getPayload()).getResult();
        if (!ids.contains(id))
            throw new RuntimeException("No worker with such id");

        var currentObjResp = apiClientReceiver.call(new GetWorkerCommand(id));
        if (!currentObjResp.isSuccess())
            throw new RuntimeException("Could not retrieve existing data");
        var current = ((GetWorkerCommand.GetWorkerCommandResponse) currentObjResp.getPayload()).getWorker();

        return new UpdateCommand(
                id,
                uiReceiver.fill(Worker.class, current)
        );
    }
}
