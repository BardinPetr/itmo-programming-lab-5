package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of remove_greater command
 */
@Data
public class RemoveGreaterCommand extends Command{
    public final String TYPE = "remove_greater";
    public Worker element;

}
