package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Getter
@ToString
@EqualsAndHashCode
public class SaveCommand extends Command{
    @JsonIgnore public final String TYPE = "save";

    @Override
    public String getType() {
        return TYPE;
    }
}
