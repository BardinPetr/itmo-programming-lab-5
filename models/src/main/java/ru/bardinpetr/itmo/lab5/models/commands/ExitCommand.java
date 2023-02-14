package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class ExitCommand extends Command{
    public final String TYPE = "exit";
}
