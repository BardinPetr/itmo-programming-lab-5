package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

/**
 * Class of add_if_max command
 */
@Data
public class AddIfMaxCommand extends Command{
    @JsonIgnore public final String TYPE = "add_if_max";
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
