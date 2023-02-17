package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.resonses.ListCommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of print_descending command
 */
@Data
@NoArgsConstructor
public class PrintDescendingCommand extends Command {

    @Override
    public String getType() {
        return "print_descending";
    }

    @Override
    public PrintDescendingCommandResponse createResponse() {
        return new PrintDescendingCommandResponse();
    }

    public static class PrintDescendingCommandResponse extends ListCommandResponse<Worker> {
    }
}
