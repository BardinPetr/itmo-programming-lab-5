package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class of remove_greater command
 */
@Data
public class RemoveGreaterCommand extends Command{
    @JsonIgnore public final String TYPE = "remove_greater";
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
