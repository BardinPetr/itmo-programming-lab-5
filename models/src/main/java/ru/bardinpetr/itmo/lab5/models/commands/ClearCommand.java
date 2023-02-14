package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Class of clear command
 */
@Getter
@ToString
@EqualsAndHashCode
public class ClearCommand extends Command{
    public final String TYPE = "clear";

    @Override
    public String getType() {
        return TYPE;
    }
}
