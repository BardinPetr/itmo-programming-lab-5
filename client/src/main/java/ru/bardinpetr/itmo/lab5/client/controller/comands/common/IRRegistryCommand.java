package ru.bardinpetr.itmo.lab5.client.controller.comands.common;

import ru.bardinpetr.itmo.lab5.client.controller.comands.common.exception.NoSuchCommandException;

/**
 * Interface for getting commands by name
 */
public interface IRRegistryCommand {
    /**
     *
     * @param name command name
     * @return command with required name
     * @throws NoSuchCommandException when there is no such command
     */
    AbstractLocalCommand getByName(String name) throws NoSuchCommandException;
}
