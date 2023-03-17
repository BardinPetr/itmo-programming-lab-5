package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

/**
 * Class of clear command
 */
@Data
public class ClearCommand extends APICommand {
    @Override
    public String getType() {
        return "clear";
    }
}
