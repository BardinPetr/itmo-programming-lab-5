package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Class of add_if_min command
 */
@Data
public class AddIfMinCommand extends Command{
    @JsonIgnore public final String TYPE = "add_if_min";
    public Worker element;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Field[] getInteractArgs(){
        return new Field[]{
                new Field("element", Worker.class)
        };
    }

}
