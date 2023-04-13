package ru.bardinpetr.itmo.lab5.client.api.connectors;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;

public abstract class AbstractAPIClientReceiverFactory {
    public abstract APIClientReceiver create();
}
