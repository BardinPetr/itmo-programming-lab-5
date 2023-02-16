package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of add command
 */
@Data
public class AddCommand extends Command{
    public Worker element;
    @Override
    public String getType() {
        return "Add";
    }
}
