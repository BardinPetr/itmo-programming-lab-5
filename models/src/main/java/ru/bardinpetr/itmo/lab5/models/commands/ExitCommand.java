package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ExitCommand extends Command {
    @JsonIgnore
    public final String TYPE = "exit";

    @Override
    public String getType() {
        return TYPE;
    }

}
