package ru.bardinpetr.itmo.lab5.clientgui.models.factory;

import ru.bardinpetr.itmo.lab5.clientgui.models.impl.OrganizationModel;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.WorkerModel;

public class ModelProvider {
    private static ModelProvider instance;

    private WorkerModel currentWorkers;
    private OrganizationModel currentOrganizations;

    private ModelProvider() {
        workers();
        organizations();
    }

    public static ModelProvider getInstance() {
        if (instance == null)
            instance = new ModelProvider();
        return instance;
    }

    public WorkerModel workers() {
        if (currentWorkers != null)
            return currentWorkers;

        currentWorkers = new WorkerModel(true);
        return currentWorkers;
    }

    public OrganizationModel organizations() {
        if (currentOrganizations != null)
            return currentOrganizations;

        currentOrganizations = new OrganizationModel(true);
        return currentOrganizations;
    }
}
