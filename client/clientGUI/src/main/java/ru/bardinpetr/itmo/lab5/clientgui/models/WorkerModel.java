package ru.bardinpetr.itmo.lab5.clientgui.models;

import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class WorkerModel extends ExtendedListModel<Worker> {
    private final Integer currentUserId;

    public WorkerModel(Integer currentUserId) {
        this.currentUserId = currentUserId;
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
