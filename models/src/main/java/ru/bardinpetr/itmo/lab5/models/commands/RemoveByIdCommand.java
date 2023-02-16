package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class of remove_by_id command
 */
@Data
public class RemoveByIdCommand extends Command{
    @JsonIgnore public final String TYPE = "remove_by_id";
    public int id;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Field[] getInlineArgs(){
        return new Field[]{
                new Field("id", Long.class)
        };
    }
}
