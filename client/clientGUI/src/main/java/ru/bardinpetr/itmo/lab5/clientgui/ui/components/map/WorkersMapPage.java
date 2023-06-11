package ru.bardinpetr.itmo.lab5.clientgui.ui.components.map;

import ru.bardinpetr.itmo.lab5.clientgui.models.impl.WorkerModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show.WorkerUpdateFrameFactory;
import ru.bardinpetr.itmo.lab5.events.models.Event;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class WorkersMapPage extends MapPage<Worker, WorkerModel, WorkerSprite> {

    private final Map<Integer, Color> ownerColors;

    public WorkersMapPage(WorkerModel model) {
        super(model);
        ownerColors = new HashMap<>();

        start();

        recalculateAxis();
        centerMap();
    }

    @Override
    protected void recalculateAxis() {
        var minPoint = new Point(-500, -500);
        var maxPoint = new Point(500, 500);

        model
                .asStream()
                .map(WorkerSprite::getCoordinates)
                .forEach(c -> {
                    minPoint.x = Math.min(minPoint.x, c.x);
                    minPoint.y = Math.min(minPoint.y, c.y);
                    maxPoint.x = Math.max(maxPoint.x, c.x);
                    maxPoint.y = Math.max(maxPoint.y, c.y);
                });

        setAxis(minPoint, maxPoint);
    }

    @Override
    protected WorkerSprite createSprite(Integer pk, Worker data) {
        var ws = new WorkerSprite(pk);
        ws.setOnRedrawRequest(this::repaint);
        updateSprite(pk, ws, data);
        ws.showObject();
        return ws;
    }

    @Override
    protected void updateSprite(Integer pk, WorkerSprite sprite, Worker newData) {
        var owner = newData.getOwner();
        if (!ownerColors.containsKey(owner))
            ownerColors.put(owner, Color.getHSBColor((float) (Math.random() * 360), 1, 1));

        sprite.setColor(ownerColors.get(owner));
        sprite.update(newData);

        recalculateAxis();
    }

    @Override
    protected void onClick(Worker object) {
        if (object == null)
            return;
        SwingUtilities.invokeLater(
                () -> WorkerUpdateFrameFactory
                        .getInstance()
                        .open(object, model.isEditableByCurrentUser(object))
        );
    }

    protected void onServerEvent(ru.bardinpetr.itmo.lab5.events.models.Event event) {
        if (event.getAction() == Event.EventType.DELETE) {
            sprites
                    .get((Integer) event.getObject())
                    .hideObject(() -> super.onServerEvent(event));
        } else {
            super.onServerEvent(event);
        }
    }
}
