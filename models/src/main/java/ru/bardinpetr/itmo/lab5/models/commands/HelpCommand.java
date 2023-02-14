package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
/**
 * Class of help command
 */
@Data
public class HelpCommand extends Command{
    public final String TYPE = "help";
}