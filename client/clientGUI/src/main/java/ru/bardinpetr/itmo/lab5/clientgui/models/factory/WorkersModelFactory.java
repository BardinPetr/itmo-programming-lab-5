package ru.bardinpetr.itmo.lab5.clientgui.models.factory;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.WorkerModel;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetSelfInfoCommand;

public class WorkersModelFactory {

    public static WorkerModel create() {
        Integer ownerId;
        try {
            ownerId = ((GetSelfInfoCommand.GetSelfInfoResponse) APIProvider.getConnector().call(new GetSelfInfoCommand())).getId();
        } catch (APIClientException ignored) {
            return create();
        }

        return new WorkerModel(ownerId);
    }
}
