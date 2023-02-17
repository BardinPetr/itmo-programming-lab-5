package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of update command
 */
@Data
@NoArgsConstructor
public class UpdateCommand extends Command {
    @NonNull
    public Long id;
    @NonNull
    public Worker element;

    @Override
    public String getType() {
        return "update";
    }

    @Override
    public Field[] getInlineArgs() {
        return new Field[]{
                new Field("id", Long.class)
        };
    }

    @Override
    public Field[] getInteractArgs() {
        return new Field[]{
                new Field("element", Worker.class)
        };
    }
}
