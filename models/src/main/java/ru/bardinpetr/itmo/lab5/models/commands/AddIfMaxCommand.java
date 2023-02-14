package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of add_if_max command
 */
@Data
public class AddIfMaxCommand extends Command{
    public final String TYPE = "add_if_max";
    public Worker element;

    @Override
    public String getType() {
        return TYPE;
    }
}
