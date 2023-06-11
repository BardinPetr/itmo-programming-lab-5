package ru.bardinpetr.itmo.lab5.clientgui.models.factory;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.OrganizationModel;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.WorkerModel;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetSelfInfoCommand;

public class ModelFactory {

    private static WorkerModel currentWorkers;
    private static OrganizationModel currentOrganizations;


    public static WorkerModel createWorkers() {
        if (currentWorkers != null)
            return currentWorkers;

        Integer ownerId;
        try {
            ownerId = ((GetSelfInfoCommand.GetSelfInfoResponse) APIProvider.getConnector().call(new GetSelfInfoCommand())).getId();
        } catch (APIClientException ignored) {
            return createWorkers();
        }

        currentWorkers = new WorkerModel(ownerId);
        return currentWorkers;
    }

    public static OrganizationModel createOrganizations() {
        if (currentOrganizations != null)
            return currentOrganizations;

        currentOrganizations = new OrganizationModel();
        return currentOrganizations;
    }
}
