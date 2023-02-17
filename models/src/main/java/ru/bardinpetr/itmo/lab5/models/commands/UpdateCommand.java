package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of update command
 */
@Data
public class UpdateCommand extends Command {
    @NonNull
    public Long id;
    @NonNull
    public Worker element;

    @Override
    public String getType() {
        return "update";
    }
}
