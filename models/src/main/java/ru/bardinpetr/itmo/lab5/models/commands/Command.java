package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.resonses.Response;

/**
 * General class for all commands
 */
@Data
public abstract class Command {
    public abstract String getType();

    public Response createResponse() {
        return new Response();
    }
}
