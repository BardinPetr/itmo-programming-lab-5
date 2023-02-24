package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

/**
 * Class of remove_by_id command
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RemoveByIdCommand extends Command {
    @NonNull
    public Integer id;

    @Override
    public String getType() {
        return "remove_by_id";
    }

    @Override
    public Field[] getInlineArgs() {
        return new Field[]{
                new Field("id", Integer.class)
        };
    }
}
