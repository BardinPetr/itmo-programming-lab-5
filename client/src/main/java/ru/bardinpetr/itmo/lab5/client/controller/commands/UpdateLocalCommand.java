package ru.bardinpetr.itmo.lab5.client.controller.commands;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.controller.common.APIUILocalCommand;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetWorkerCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetWorkerIdsCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.UpdateCommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.UserAPICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.util.Map;


/**
 * Worker update command
 */
public class UpdateLocalCommand extends APIUILocalCommand {

    public UpdateLocalCommand(APIClientReceiver api, UIReceiver ui) {
        super(api, ui);
    }

    @Override
    public String getExternalName() {
        return "update";
    }

    @Override
    protected UserAPICommand retrieveAPICommand(String name) {
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

        APICommandResponse availableIdsResp;
        try {
            availableIdsResp = apiClientReceiver.call(new GetWorkerIdsCommand());
        } catch (APIClientException e) {
            // TODO proper api error handling
            throw new RuntimeException(e);
        }
        if (!availableIdsResp.isSuccess())
            throw new RuntimeException("Could not retrieve existing data");

        var ids = ((GetWorkerIdsCommand.GetWorkerIdsCommandResponse) availableIdsResp).getResult();
        if (!ids.contains(id))
            throw new RuntimeException("No worker with such id");

        APICommandResponse currentObjResp;
        try {
            currentObjResp = apiClientReceiver.call(new GetWorkerCommand(id));
        } catch (APIClientException e) {
            throw new RuntimeException(e);
        }
        if (!currentObjResp.isSuccess())
            throw new RuntimeException("Could not retrieve existing data");
        var current = ((GetWorkerCommand.GetWorkerCommandResponse) currentObjResp).getWorker();

        return new UpdateCommand(
                id,
                uiReceiver.fill(Worker.class, current)
        );
    }
}
