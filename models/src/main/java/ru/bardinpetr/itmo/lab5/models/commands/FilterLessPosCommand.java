package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.data.Position;
/**
 * Class of filter_less_than_position command
 */
@Data
@NoArgsConstructor
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
