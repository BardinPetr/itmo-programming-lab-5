package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import java.util.TreeSet;

/**
 * Class of add command
 */
@Data
public class AddCommand extends Command {
    @JsonIgnore public final String TYPE = "add";
    public Worker element;
    @Override
    public String getType() {
        return TYPE;
    }
    @Override
    public Field[] getInteractArgs() {
        return new Field[]{
                new Field("element", Worker.class)
        };
    }
}
