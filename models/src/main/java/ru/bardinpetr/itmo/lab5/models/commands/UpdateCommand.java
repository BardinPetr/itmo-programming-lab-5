package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Class of update command
 */
@Data
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
