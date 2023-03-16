package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

@Data
public class SaveCommand extends APICommand {

    @Override
    public String getType() {
        return "save";
    }
}
