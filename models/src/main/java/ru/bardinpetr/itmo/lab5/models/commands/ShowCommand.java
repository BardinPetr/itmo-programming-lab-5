package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;

/**
 * Class of show command
 */
@Data
public class ShowCommand extends Command{
    public final String TYPE = "show";
}
