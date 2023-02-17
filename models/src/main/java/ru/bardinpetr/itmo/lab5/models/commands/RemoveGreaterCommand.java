package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of remove_greater command
 */
@Data
public class RemoveGreaterCommand extends Command {
    @NonNull
    public Worker element;

    @Override
    public String getType() {
        return "remove_greater";
    }

}
