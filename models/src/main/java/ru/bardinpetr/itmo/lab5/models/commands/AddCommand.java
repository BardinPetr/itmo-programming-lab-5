package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of add command
 */
@Data
@NoArgsConstructor
public class AddCommand extends Command {
    @NonNull
    public Worker element;

    @Override
    public String getType() {
        return "add";
    }

    @Override
    public Field[] getInteractArgs() {
        return new Field[]{
                new Field("element", Worker.class)
        };
    }
}
