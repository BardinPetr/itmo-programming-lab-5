package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.data.Position;

/**
 * Class of filter_less_than_position command
 */
@Data
public class FilterLessPosCommand extends Command{
    public final String TYPE = "filter_less_than_position";
    public Position position;

    @Override
    public String getType() {
        return TYPE;
    }

}
