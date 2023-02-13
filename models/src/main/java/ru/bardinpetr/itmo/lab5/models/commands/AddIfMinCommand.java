package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of add_if_min command
 */
@Data
public class AddIfMinCommand extends Command{
    public final String TYPE = "add_if_min";
    public Worker element;
}
