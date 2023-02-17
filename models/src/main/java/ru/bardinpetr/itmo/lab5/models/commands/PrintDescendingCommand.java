package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * Class of print_descending command
 */
@Data
public class PrintDescendingCommand extends Command {
    @JsonIgnore
    public final String TYPE = "print_descending";

    @Override
    public String getType() {
        return TYPE;
    }

}
