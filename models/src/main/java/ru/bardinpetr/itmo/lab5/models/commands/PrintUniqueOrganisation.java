package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;

/**
 * Class of print_unique_organization  command
 */

@Data
public class PrintUniqueOrganisation extends Command{
    public final String TYPE = "print_unique_organization ";

    @Override
    public String getType() {
        return TYPE;
    }
}
