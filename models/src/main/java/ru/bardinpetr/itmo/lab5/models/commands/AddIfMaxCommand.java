package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of add_if_max command
 */
@Data
@NoArgsConstructor
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
