package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
/**
 * Class of clear command
 */
@Data
public class ClearCommand extends Command{
    @JsonIgnore public final String TYPE = "clear";

    @Override
    public String getType() {
        return TYPE;
    }

}
