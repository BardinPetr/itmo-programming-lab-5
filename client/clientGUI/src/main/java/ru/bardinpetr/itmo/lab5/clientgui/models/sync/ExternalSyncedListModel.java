package ru.bardinpetr.itmo.lab5.clientgui.models.sync;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.clientgui.models.ExtendedListModel;
import ru.bardinpetr.itmo.lab5.clientgui.utils.EventUtils;
import ru.bardinpetr.itmo.lab5.events.client.consumers.ResourceEventConsumer;
import ru.bardinpetr.itmo.lab5.events.models.Event;
import ru.bardinpetr.itmo.lab5.events.models.EventSet;
import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

public class ExternalSyncedListModel<T extends IKeyedEntity<Integer>> extends ExtendedListModel<T> {

    private final APIClientConnector connector;
    private final String resourceId;
    private final ScheduledExecutorService executor;
    private final ResourceEventConsumer eventSubscriber;
    private Supplier<List<T>> getAll;
    private Function<Integer, T> getSingle;

    public ExternalSyncedListModel(String resourceId) {
        super();
        this.resourceId = resourceId;

        connector = APIProvider.getConnector();
        executor = Executors.newSingleThreadScheduledExecutor();

        eventSubscriber = new ResourceEventConsumer(this::onUpdate, resourceId);
    }

    public void setLoaders(Supplier<List<T>> getAll, Function<Integer, T> getSingle) {
        this.getAll = getAll;
        this.getSingle = getSingle;
        SwingUtilities.invokeLater(this::firstPool);
    }

    private void firstPool() {
        try {
            var data = getAll.get();
            if (data != null) {
                addAll(data);

                APIProvider
                        .getPoolingEventSource()
                        .subscribe(eventSubscriber);
                return;
            }
        } catch (Exception ignored) {
        }

        executor.schedule(this::firstPool, 5, TimeUnit.SECONDS);
    }

    private void onCreateEvent(Integer id) {
        var obj = getSingle.apply(id);
        if (obj != null) {
            addElement(obj);
            return;
        }

        executor.schedule(() -> onCreateEvent(id), 5, TimeUnit.SECONDS);
    }

    private void onUpdateEvent(Integer id) {
        var obj = getSingle.apply(id);
        if (obj != null) {
            var realId = getVectorPos(id);
            if (realId == -1)
                addElement(obj);
            else
                setElementAt(obj, realId);
            return;
        }

        executor.schedule(() -> onUpdateEvent(id), 5, TimeUnit.SECONDS);
    }

    private void onDeleteEvent(Integer id) {
        var realId = getVectorPos(id);
        if (realId != -1)
            removeElementAt(realId);
    }

    private void onUpdate(EventSet eventSet) {
        for (var i : eventSet.getEvents()) {
            var id = (Integer) i.getObject();
            fireBaseEvent(i);
            switch (i.getAction()) {
                case DELETE -> onDeleteEvent(id);
                case CREATE -> onCreateEvent(id);
                case UPDATE -> onUpdateEvent(id);
            }
        }
    }

    public void addServerEventListener(ServerEventListener l) {
        listenerList.add(ServerEventListener.class, l);
    }

    public void removeServerEventListener(ServerEventListener l) {
        listenerList.remove(ServerEventListener.class, l);
    }

    protected void fireBaseEvent(Event evt) {
        EventUtils.fireAll(
                listenerList,
                ServerEventListener.class,
                i -> i.onEvent(evt)
        );
    }

    public int getVectorPos(Integer id) {
        for (int pos = 0; pos < size(); pos++)
            if (getElementAt(pos).getPrimaryKey().equals(id))
                return pos;
        return -1;
    }

}

