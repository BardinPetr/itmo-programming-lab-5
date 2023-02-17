package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

@Data
@NoArgsConstructor
public class ExitCommand extends Command {
    @Override
    public String getType() {
        return "exit";
    }
}
