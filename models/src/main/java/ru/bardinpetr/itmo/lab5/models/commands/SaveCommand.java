package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class SaveCommand extends Command {
    @JsonIgnore
    public final String TYPE = "save";

    @Override
    public String getType() {
        return TYPE;
    }
}
