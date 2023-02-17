package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of remove_greater command
 */
@Data
@NoArgsConstructor
public class RemoveGreaterCommand extends Command {
    @NonNull
    public Worker element;

    @Override
    public String getType() {
        return "remove_greater";
    }

    @Override
    public Field[] getInteractArgs() {
        return new Field[]{
                new Field("element", Worker.class)
        };
    }

}
