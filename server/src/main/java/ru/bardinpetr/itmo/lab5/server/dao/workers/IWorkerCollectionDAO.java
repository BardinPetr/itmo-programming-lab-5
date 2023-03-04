package ru.bardinpetr.itmo.lab5.server.dao.workers;

import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.Position;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.server.dao.ICollectionFilteredDAO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface IWorkerCollectionDAO extends ICollectionFilteredDAO<Integer, Worker> {
    default void addIfMax(Worker worker) {
        var curMax = getMax();
        if (curMax.compareTo(worker) < 0)
            add(worker);
        else
            throw new RuntimeException("Not maximum");
    }

    default void addIfMin(Worker worker) {
        var curMin = getMin();
        if (curMin.compareTo(worker) > 0)
            add(worker);
        else
            throw new RuntimeException("Not minimum");
    }

    default void removeIfGreater(Worker worker) {
        remove(cur -> cur.compareTo(worker) > 0);
    }

    default List<Worker> filterLessThanPosition(Position position) {
        return filter(worker -> worker.getPosition().compareTo(position) < 0);
    }

    default Set<Organization> getUniqueOrganizations() {
        return readAll().stream().map(Worker::getOrganization).collect(Collectors.toSet());
    }
}
