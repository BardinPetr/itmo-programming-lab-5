package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;

/**
 * Class of print_descending command
 */
@Data
public class PrintDescendingCommand extends Command{
    public final String TYPE = "print_descending";

}
