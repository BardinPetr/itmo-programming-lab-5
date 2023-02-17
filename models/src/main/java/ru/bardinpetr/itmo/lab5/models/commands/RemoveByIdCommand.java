package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

/**
 * Class of remove_by_id command
 */
@Data
public class RemoveByIdCommand extends Command {
    @NonNull
    public Long id;

    @Override
    public String getType() {
        return "removeById";
    }
}
