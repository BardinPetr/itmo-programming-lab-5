package ru.bardinpetr.itmo.lab5.clientgui.ui.components.map;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import lombok.Setter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.animation.PropertyAnimator;
import ru.bardinpetr.itmo.lab5.clientgui.ui.animation.impl.PointAnimator;
import ru.bardinpetr.itmo.lab5.models.data.Position;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import java.awt.*;

public class WorkerSprite extends JPanel {

    private static final int ANIMATION_SPEED_PXS = 50;
    private static final int NAME_LENGTH_MAX = 15;
    private static final int ICON_SIZE = 50;
    private final PropertyAnimator<Point> positionAnimator;
    private Worker workerData;
    @Setter
    private Color color;
    private Point currentPosition;
    private Icon currentIcon;
    private String currentName;
    private Runnable redrawRequest;

    public WorkerSprite() {
        setVisible(true);
        setLayout(null);
        setOpaque(false);

        positionAnimator = new PointAnimator(ANIMATION_SPEED_PXS);
        positionAnimator.addListener(this::setCurrentPosition);
    }

    public static Point getCoordinates(Worker w) {
        var c = w.getCoordinates();
        return new Point(c.getX(), (int) -c.getY());
    }

    public void update(Worker data) {
        if (workerData != null) {
            if (!data.getCoordinates().equals(workerData.getCoordinates()))
                positionAnimator.animate(getCoordinates(data));
        } else {
            currentPosition = getCoordinates(data);
            positionAnimator.setCurrentStatus(currentPosition);
        }

        workerData = data;

        var name = data.getName();
        if (name.length() > NAME_LENGTH_MAX) currentName = name.substring(0, NAME_LENGTH_MAX) + "...";
        else currentName = name;

        var size = (int) (ICON_SIZE * getWorkerSizeMultiplier());
        currentIcon = IconFontSwing.buildIcon(FontAwesome.USER, size, color);

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;
        super.paintComponent(g);

        if (workerData == null) return;

        var size = (int) (ICON_SIZE * getWorkerSizeMultiplier());
        currentIcon.paintIcon(null, g, currentPosition.x, currentPosition.y);

        var nameSize = g.getFont().getStringBounds(currentName, g2.getFontRenderContext());

        g.setColor(Color.RED);
        var circleSize = 8;
        g.fillOval(currentPosition.x - circleSize / 2, currentPosition.y - circleSize / 2, circleSize, circleSize);

        g.setColor(Color.BLACK);
        // Put name centered
        g.drawString(
                currentName,
                currentPosition.x + (currentIcon.getIconWidth() - (int) nameSize.getWidth()) / 2,
                currentPosition.y + size + 5
        );
    }

    @Override
    public void paint(Graphics g) {
        paintComponent(g);
    }

    private float getWorkerSizeMultiplier() {
        if (workerData.getPosition() == null) return 1;
        return 1 + (float) workerData.getPosition().value / Position.values().length;
    }

    protected void setCurrentPosition(Point state, boolean running) {
        currentPosition = state;
        redrawRequest.run();
    }

    public void setOnRedrawRequest(Runnable target) {
        this.redrawRequest = target;
    }
}
