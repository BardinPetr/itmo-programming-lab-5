package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.resonses.ListCommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Position;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of filter_less_than_position command
 */
@Data
public class FilterLessPosCommand extends Command {
    @NonNull
    public Position position;

    @Override
    public String getType() {
        return "filter_less_than_position";
    }

    @Override
    public FilterLessPosCommandResponse createResponse() {
        return new FilterLessPosCommandResponse();
    }

    public static class FilterLessPosCommandResponse extends ListCommandResponse<Worker> {
    }

}
