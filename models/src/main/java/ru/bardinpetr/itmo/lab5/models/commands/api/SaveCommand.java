package ru.bardinpetr.itmo.lab5.models.commands.api;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.APICommand;

/**
 * Class of save command
 */
@Data
public class SaveCommand extends APICommand {
    @Override
    public String getType() {
        return "save";
    }
}
