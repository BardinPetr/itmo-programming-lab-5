package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
/**
 * Class of update command
 */
@Data
public class UpdateCommand extends Command{
    public final String TYPE = "update";
    public int id;
    public Worker element;

    @Override
    public String getType() {
        return TYPE;
    }
}
