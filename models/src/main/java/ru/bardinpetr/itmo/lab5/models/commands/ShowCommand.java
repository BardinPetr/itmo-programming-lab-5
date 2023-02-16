package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class of show command
 */
@Data
public class ShowCommand extends Command{
    @JsonIgnore public final String TYPE = "show";

    @Override
    public String getType() {
        return TYPE;
    }
}
