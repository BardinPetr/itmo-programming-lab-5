package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Class of help command
 */
@Data
public class HelpCommand extends Command{
    @JsonIgnore public final String TYPE = "help";

    @Override
    public String getType() {
        return TYPE;
    }
}