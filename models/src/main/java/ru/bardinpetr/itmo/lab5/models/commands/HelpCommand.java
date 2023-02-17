package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * Class of help command
 */
@Data
public class HelpCommand extends Command {
    @JsonIgnore
    public final String TYPE = "help";

    @Override
    public String getType() {
        return TYPE;
    }
}