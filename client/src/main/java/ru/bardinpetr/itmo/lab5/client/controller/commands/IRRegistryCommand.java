package ru.bardinpetr.itmo.lab5.client.controller.commands;

import ru.bardinpetr.itmo.lab5.client.controller.common.UILocalCommand;

public interface IRRegistryCommand {
    UILocalCommand getByName(String commandName);
}
