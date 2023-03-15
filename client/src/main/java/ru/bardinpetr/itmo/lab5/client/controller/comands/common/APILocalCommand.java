package ru.bardinpetr.itmo.lab5.client.controller.comands.common;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;

public class APILocalCommand extends AbstractLocalCommand {

    private APIClientReceiver apiClient;

    public APIClientReceiver getApiClient() {
        if (apiClient == null)
            throw new RuntimeException("Not initialized");
        return apiClient;
    }

    public void setApiClient(APIClientReceiver apiClient) {
        this.apiClient = apiClient;
    }


    @Override
    public void execute(String commandName, Object[] inlineArgs) {

    }
}
