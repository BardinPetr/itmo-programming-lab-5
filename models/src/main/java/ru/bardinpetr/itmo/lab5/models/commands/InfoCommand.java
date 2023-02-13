package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;

@Data
public class InfoCommand extends Command{
    public final String TYPE = "info";
}
