package ru.bardinpetr.itmo.lab5.clientgui.models.impl;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.models.sync.ExternalSyncedListModel;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetSelfInfoCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetWorkerCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.ShowCommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.PagingAPICommand;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class WorkerModel extends ExternalSyncedListModel<Worker> {
    private Integer currentUserId = -1;

    public WorkerModel(boolean autoSync) {
        super(autoSync, "worker");

        setLoaders(this::loadAll, this::loadOne);
        new Thread(this::loadOwner).start();
    }

    private Worker loadOne(Integer integer) {
        try {
            var res = APIProvider
                    .getConnector()
                    .call(new GetWorkerCommand(integer));
            return ((GetWorkerCommand.GetWorkerCommandResponse) res).getWorker();
        } catch (Throwable ignored) {
            return null;
        }
    }

    private List<Worker> loadAll() {
        try {
            var res = APIProvider
                    .getConnector()
                    .call(new ShowCommand(0, PagingAPICommand.FULL_COUNT));
            return ((ShowCommand.ShowCommandResponse) res).getResult();
        } catch (Throwable ignored) {
            return null;
        }
    }

    private void loadOwner() {
        try {
            currentUserId = ((GetSelfInfoCommand.GetSelfInfoResponse)
                    APIProvider.getConnector().call(new GetSelfInfoCommand())).getId();
            return;
        } catch (Throwable ignored) {
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
        loadOwner();
    }

    public boolean isEditableByCurrentUser(Worker e) {
        return e.getOwner().equals(currentUserId);
    }

    public NavigableSet<Integer> getOwners() {
        return asStream()
                .map(Worker::getOwner)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
