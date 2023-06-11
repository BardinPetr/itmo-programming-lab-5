package ru.bardinpetr.itmo.lab5.clientgui.models.impl;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.models.sync.ExternalSyncedListModel;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetOrganizationCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetOrgsCommand;
import ru.bardinpetr.itmo.lab5.models.data.Organization;

import java.util.List;

public class OrganizationModel extends ExternalSyncedListModel<Organization> {

    public OrganizationModel() {
        super("organization");
        setLoaders(this::loadAll, this::loadOne);
    }

    private Organization loadOne(Integer integer) {
        try {
            var res = APIProvider
                    .getConnector()
                    .call(new GetOrganizationCommand(integer));
            return ((GetOrganizationCommand.GetOrganizationCommandResponse) res).getOrganization();
        } catch (Throwable ignored) {
            return null;
        }
    }

    private List<Organization> loadAll() {
        try {
            var res = APIProvider
                    .getConnector()
                    .call(new GetOrgsCommand());
            return ((GetOrgsCommand.OrganisationCommandResponse) res).getOrganizations();
        } catch (Throwable ignored) {
            return null;
        }
    }
}
