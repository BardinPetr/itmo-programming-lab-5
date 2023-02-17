package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.resonses.ListCommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.bardinpetr.itmo.lab5.models.commands.resonses.ICommandResponse;

/**
 * Class of show command
 */
@Data
@NoArgsConstructor
public class ShowCommand extends Command {
    @Override
    public String getType() {
        return "show";
    }

    @Override
    public ShowCommandResponse createResponse() {
        return new ShowCommandResponse();
    }

    public static class ShowCommandResponse extends ListCommandResponse<Worker> {
    }
}
