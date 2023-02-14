package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

//@Jacksonized

/**
 * Class of add command
 */
@Data
public class AddCommand extends Command{
    public Worker element;
    @Override
    public String getType() {
        return "Add";
    }
}
