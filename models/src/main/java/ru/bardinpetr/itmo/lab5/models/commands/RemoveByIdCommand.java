package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;

/**
 * Class of remove_by_id command
 */
@Data
public class RemoveByIdCommand extends Command{
    public final String TYPE = "removeById";
    public int id;
}
