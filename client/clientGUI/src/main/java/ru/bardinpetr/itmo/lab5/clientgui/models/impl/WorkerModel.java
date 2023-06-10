package ru.bardinpetr.itmo.lab5.clientgui.models.impl;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.models.ExternalSyncedListModel;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetWorkerCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.ShowCommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.PagingAPICommand;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class WorkerModel extends ExternalSyncedListModel<Worker> {
    private final Integer currentUserId;

    public WorkerModel(Integer currentUserId) {
        super("worker");
        setLoaders(this::loadAll, this::loadOne);

        this.currentUserId = currentUserId;
    }

    private Worker loadOne(Integer integer) {
        try {
            var res = APIProvider
                    .getConnector()
                    .call(new GetWorkerCommand(integer));
            return ((GetWorkerCommand.GetWorkerCommandResponse) res).getWorker();
        } catch (Exception | APIClientException ignored) {
            return null;
        }
    }

    private List<Worker> loadAll() {
        try {
            var res = APIProvider
                    .getConnector()
                    .call(new ShowCommand(0, PagingAPICommand.FULL_COUNT));
            return ((ShowCommand.ShowCommandResponse) res).getResult();
        } catch (Exception | APIClientException ignored) {
            return null;
        }
    }

    public boolean isEditableByCurrentUser(Worker e) {
        return e.getOwner().equals(currentUserId);
    }

    public NavigableSet<Integer> getOwners() {
        return asStream()
                .map(Worker::getOwner)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Worker getByPK(int id) {
        return asStream()
                .filter(i -> i.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
