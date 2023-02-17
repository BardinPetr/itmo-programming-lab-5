package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class of remove_by_id command
 */
@Data
@NoArgsConstructor
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
