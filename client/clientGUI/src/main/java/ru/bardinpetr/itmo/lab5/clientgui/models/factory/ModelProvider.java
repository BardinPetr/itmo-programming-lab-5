package ru.bardinpetr.itmo.lab5.clientgui.models.factory;

import ru.bardinpetr.itmo.lab5.clientgui.models.impl.OrganizationModel;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.WorkerModel;

public class ModelProvider {

    private static WorkerModel currentWorkers;
    private static OrganizationModel currentOrganizations;


    public static WorkerModel workers() {
        if (currentWorkers != null)
            return currentWorkers;

        currentWorkers = new WorkerModel();
        return currentWorkers;
    }

    public static OrganizationModel organizations() {
        if (currentOrganizations != null)
            return currentOrganizations;

        currentOrganizations = new OrganizationModel();
        return currentOrganizations;
    }
}
