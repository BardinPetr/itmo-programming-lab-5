package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

/**
 * Class of remove_by_id command
 */
@Data
@NoArgsConstructor
public class RemoveByIdCommand extends Command {
    @NonNull
    public Long id;

    @Override
    public String getType() {
        return "removeById";
    }

    @Override
    public Field[] getInlineArgs(){
        return new Field[]{
                new Field("id", Long.class)
        };
    }
}
