package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

//@Jacksonized
@Data
public class AddCommand extends Command{
    public final String TYPE = "add";
    public Worker element;

}
