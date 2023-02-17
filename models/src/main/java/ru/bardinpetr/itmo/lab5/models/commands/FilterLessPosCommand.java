package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.resonses.ListCommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Position;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of filter_less_than_position command
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
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

    @Override
    public Field[] getInlineArgs() {
        return new Field[]{
                new Field("position", Position.class)
        };
    }

    public static class FilterLessPosCommandResponse extends ListCommandResponse<Worker> {
    }
}
