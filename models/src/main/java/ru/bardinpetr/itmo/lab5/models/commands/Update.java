package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import ru.bardinpetr.itmo.lab5.models.data.Worker;


@Jacksonized
@Builder
public class Update extends Command{
    public final String TYPE = "show";
    public long id;
    public Worker element;
}
