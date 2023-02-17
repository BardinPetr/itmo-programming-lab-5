package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

@Data
public class SaveCommand extends Command {

    @Override
    public String getType() {
        return "save";
    }
}
