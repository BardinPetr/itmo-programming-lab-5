package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

/**
 * Class of help command
 */
@Data
@NoArgsConstructor
public class HelpCommand extends Command {

    @Override
    public String getType() {
        return "help";
    }
}