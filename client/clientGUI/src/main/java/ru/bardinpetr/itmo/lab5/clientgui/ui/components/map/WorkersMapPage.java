package ru.bardinpetr.itmo.lab5.clientgui.ui.components.map;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.api.APIConnectorFactory;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.WorkerModel;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetSelfInfoCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.AuthCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.PasswordLoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WorkersMapPage extends MapPage<Worker, WorkerModel, WorkerSprite> {

    private final Map<Integer, Color> ownerColors;

    public WorkersMapPage(WorkerModel model) {
        super(model);
        ownerColors = new HashMap<>();

        start();

        recalculateAxis();
        centerMap();
    }

    public static void main(String[] args) throws APIClientException {
        UIResources.getInstance();
        APIConnectorFactory.create();

        var loginCmd = new PasswordLoginCommand();
        loginCmd.setCredentials(new DefaultAuthenticationCredentials("u", "p"));
        APIProvider.getCredentialsStorage().setCredentials(new StoredJWTCredentials((JWTLoginResponse) ((AuthCommand.AuthCommandResponse) APIProvider.getConnector().call(loginCmd)).getData()));

        Integer ownerId = null;
        try {
            ownerId = ((GetSelfInfoCommand.GetSelfInfoResponse) APIProvider.getConnector().call(new GetSelfInfoCommand())).getId();
        } catch (APIClientException ignored) {
        }

        var model = new WorkerModel(ownerId);

        var f = new JFrame();
        f.getContentPane().add(new WorkersMapPage(model));
        f.setSize(600, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
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
        data.getCoordinates().setX((new Random()).nextInt(-600, 600));
        data.getCoordinates().setY((new Random()).nextInt(-600, 600));

        var ws = new WorkerSprite();
        ws.setOnRedrawRequest(this::repaint);
        updateSprite(pk, ws, data);
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
}
