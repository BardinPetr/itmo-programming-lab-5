package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Organization;

import java.util.List;

/**
 * Class of print_unique_organization  command
 */

@Data
public class UniqueOrganisationCommand extends APICommand {
    @Override
    public String getType() {
        return "print_unique_organization";
    }

    @Override
    public UniqueOrganisationCommandResponse createResponse() {
        return new UniqueOrganisationCommandResponse();
    }

    public static class UniqueOrganisationCommandResponse implements ICommandResponse {
        private List<Organization> organizations;

        public List<Organization> getOrganizations() {
            return organizations;
        }

        public void setOrganizations(List<Organization> organizations) {
            this.organizations = organizations;
        }

        @Override
        public String getUserMessage() {
            String s = "organization field unique values:\n";
            for (Organization i : organizations) {
                s += String.format(
                        "\tFull name: %s, type: %s\n",
                        i.getFullName(),
                        i.getType()
                );
            }
            return s;
        }
    }
}
