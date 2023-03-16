package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ListCommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Position;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

/**
 * Class of filter_less_than_position command
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class FilterLessPosCommand extends APICommand {
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
        @Override
        public String getUserMessage() {
            var result = getResult();
            return "elements whose position field value is less than the given one" +
                    Worker.nicePrintFormat(result);
        }
    }
}
