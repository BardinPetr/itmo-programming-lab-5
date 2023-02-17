package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
/**
 * Class of update command
 */
@Data
@NoArgsConstructor
public class UpdateCommand extends Command{
    @JsonIgnore public final String TYPE = "update";
    public int id;
    public Worker element;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Field[] getInlineArgs() {
        return new Field[]{
                new Field("id", Long.class)
        };
    }

    @Override
    public Field[] getInteractArgs(){
        return new Field[]{
                new Field("element", Worker.class)
        };
    }
}
