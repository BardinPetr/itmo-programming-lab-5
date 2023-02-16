package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Getter
@ToString
@EqualsAndHashCode
public class ExitCommand extends Command{
    @JsonIgnore public final String TYPE = "exit";

    @Override
    public String getType() {
        return TYPE;
    }

}
