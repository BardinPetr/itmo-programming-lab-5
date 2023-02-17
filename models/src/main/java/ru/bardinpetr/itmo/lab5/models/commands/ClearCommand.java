package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

/**
 * Class of clear command
 */
@Data
public class ClearCommand extends Command {

    @Override
    public String getType() {
        return "clear";
    }
}
