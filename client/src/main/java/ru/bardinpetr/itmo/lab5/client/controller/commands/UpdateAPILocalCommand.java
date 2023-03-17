package ru.bardinpetr.itmo.lab5.client.controller.commands;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.controller.common.APILocalCommand;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.tui.cli.UIReceiver;
import ru.bardinpetr.itmo.lab5.client.tui.exception.NoSuchDataException;
import ru.bardinpetr.itmo.lab5.models.commands.GetWorkerCommand;
import ru.bardinpetr.itmo.lab5.models.commands.GetWorkerIdsCommand;
import ru.bardinpetr.itmo.lab5.models.commands.UpdateCommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.util.Map;


public class UpdateAPILocalCommand extends APILocalCommand {

    public UpdateAPILocalCommand(APIClientReceiver api, UIReceiver ui) {
        super(api, ui);
    }

    @Override
    protected APICommand retrieveAPICommand(String name) {
        return new UpdateCommand();
    }

    @Override
    protected APICommand prepareAPIMessage(String name, Map<String, Object> args) throws ParserException, NoSuchDataException {
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
