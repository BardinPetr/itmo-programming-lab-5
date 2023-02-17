package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.resonses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Organization;

import java.util.Set;

/**
 * Class of print_unique_organization  command
 */

@Data
@NoArgsConstructor
public class UniqueOrganisationCommand extends Command {
    @Override
    public String getType() {
        return "print_unique_organization";
    }

    @Override
    public UniqueOrganisationCommandResponse createResponse() {
        return new UniqueOrganisationCommandResponse();
    }

    public static class UniqueOrganisationCommandResponse implements ICommandResponse {
        private Set<Organization> organizations;

        public Set<Organization> getOrganizations() {
            return organizations;
        }

        public void setOrganizations(Set<Organization> organizations) {
            this.organizations = organizations;
        }

        @Override
        public String getUserMessage() {
            return getOrganizations().toString();
        }
    }
}
