package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;

/**
 * Class of info command
 */
@Data
public class InfoCommand extends Command{
    public final String TYPE = "info";

    @Override
    public String getType() {
        return TYPE;
    }
}
