package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.data.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Class of filter_less_than_position command
 */
@Data
public class FilterLessPosCommand extends Command{
    @JsonIgnore public final String TYPE = "filter_less_than_position";
    public Position position;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Field[] getInlineArgs(){
        return new Field[]{
                new Field("position", Position.class)
        };
    }
}
