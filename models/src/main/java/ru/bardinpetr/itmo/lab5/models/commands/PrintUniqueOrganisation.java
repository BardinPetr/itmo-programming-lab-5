package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class of print_unique_organization  command
 */

@Data
public class PrintUniqueOrganisation extends Command{
    @JsonIgnore public final String TYPE = "print_unique_organization";

    @Override
    public String getType() {
        return TYPE;
    }
}
