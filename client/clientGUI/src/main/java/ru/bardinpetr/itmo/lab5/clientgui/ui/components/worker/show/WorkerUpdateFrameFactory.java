package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show;

import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.update.WorkerUpdateFrameZ;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.util.HashMap;
import java.util.Map;

public class WorkerUpdateFrameFactory {

    private static final Map<Integer, WorkerUpdateFrameZ> cache = new HashMap<>();
    private static WorkerUpdateFrameFactory instance;

    public static WorkerUpdateFrameFactory getInstance() {
        if (instance == null)
            instance = new WorkerUpdateFrameFactory();
        return instance;
    }

    public WorkerUpdateFrameZ open(Worker worker, boolean editable) {
        var id = worker.getPrimaryKey();
        var existing = cache.getOrDefault(id, null);
        if (existing != null && existing.isShowing())
            return existing;

        var newPanel = new WorkerUpdateFrameZ(worker, editable);
        newPanel.setVisible(true);
        cache.put(id, newPanel);
        return newPanel;
    }

}
