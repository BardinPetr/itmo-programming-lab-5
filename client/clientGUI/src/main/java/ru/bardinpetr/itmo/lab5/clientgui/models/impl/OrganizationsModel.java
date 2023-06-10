package ru.bardinpetr.itmo.lab5.clientgui.models.impl;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.models.sync.ExternalSyncedListModel;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetOrgsCommand;
import ru.bardinpetr.itmo.lab5.models.data.Organization;

import java.util.List;

public class OrganizationsModel extends ExternalSyncedListModel<Organization> {

    public OrganizationsModel() {
        super("organization");
        setLoaders(this::loadAll, this::loadOne);
    }

    private Organization loadOne(Integer id) {
        var all = loadAll();
        if (all == null)
            return null;
        return all.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
    }

    private List<Organization> loadAll() {
        try {
            var res = APIProvider
                    .getConnector()
                    .call(new GetOrgsCommand());
            return ((GetOrgsCommand.OrganisationCommandResponse) res).getOrganizations();
        } catch (Exception | APIClientException ignored) {
            return null;
        }
    }
}
