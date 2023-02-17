package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

@Getter
@ToString
@EqualsAndHashCode
public class SaveCommand extends Command {

    @Override
    public String getType() {
        return "save";
    }
}
