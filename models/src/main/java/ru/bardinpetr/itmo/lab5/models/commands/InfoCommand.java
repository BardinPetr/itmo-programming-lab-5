package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * Class of info command
 */
@Data
public class InfoCommand extends Command{
    @JsonIgnore public final String TYPE = "info";

    @Override
    public String getType() {
        return TYPE;
    }
}
