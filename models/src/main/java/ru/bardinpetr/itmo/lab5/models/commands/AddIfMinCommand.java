package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

/**
 * Class of add_if_min command
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AddIfMinCommand extends Command {
    @NonNull
    public Worker element;

    @Override
    public String getType() {
        return "add_if_min";
    }

    @Override
    public Field[] getInteractArgs() {
        return new Field[]{
                new Field("element", Worker.class)
        };
    }

}
