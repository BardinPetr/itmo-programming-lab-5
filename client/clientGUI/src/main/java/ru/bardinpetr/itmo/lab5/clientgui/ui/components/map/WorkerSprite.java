package ru.bardinpetr.itmo.lab5.clientgui.ui.components.map;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import lombok.Setter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.animation.PropertyAnimator;
import ru.bardinpetr.itmo.lab5.clientgui.ui.animation.impl.DoubleAnimator;
import ru.bardinpetr.itmo.lab5.clientgui.ui.animation.impl.PointAnimator;
import ru.bardinpetr.itmo.lab5.models.data.Position;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import java.awt.*;

public class WorkerSprite extends MapSprite {

    private static final int ANIMATION_SPEED_PXS = 75;
    private static final int NAME_LENGTH_MAX = 15;
    private static final int ICON_SIZE = 50;
    private final PropertyAnimator<Point> positionAnimator;
    private final PropertyAnimator<Double> transparencyAnimator;
    private final Integer workerID;
    private Worker workerData;
    @Setter
    private Color color;
    private Point currentPosition;
    private Icon currentIcon;
    private String currentName;
    private Runnable redrawRequest;
    private Double opacity = 0d;
    private Runnable endHandler;

    public WorkerSprite(Integer workerID) {
        this.workerID = workerID;

        setVisible(true);
        setLayout(null);
        setOpaque(false);

        positionAnimator = new PointAnimator(ANIMATION_SPEED_PXS);
        positionAnimator.addListener(this::setCurrentPosition);

        transparencyAnimator = new DoubleAnimator(0.0005);
        transparencyAnimator.addListener(this::setOpacity);
        transparencyAnimator.setCurrentStatus(0d);
    }

    public static Point getCoordinates(Worker w) {
        var c = w.getCoordinates();
        return new Point(c.getX(), (int) -c.getY());
    }

    @Override
    public Rectangle calculateBorder() {
        return new Rectangle(
                currentPosition.x - 10, currentPosition.y - 10,
                currentIcon.getIconWidth() + 30, currentIcon.getIconHeight() + 30
        );
    }

    public void update(Worker data) {
        if (workerData != null) {
            if (!data.getCoordinates().equals(workerData.getCoordinates()))
                positionAnimator.animate(getCoordinates(data));
        } else {
            currentPosition = getCoordinates(data);
            positionAnimator.setCurrentStatus(currentPosition);
        }

        workerData = data.clone();

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

        g2.setComposite(AlphaComposite.SrcOver.derive(opacity.floatValue()));

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
                currentPosition.y + currentIcon.getIconHeight() + 5
        );

        g2.setComposite(AlphaComposite.SrcOver);
    }

    @Override
    public void paint(Graphics g) {
        paintComponent(g);
    }

    private float getWorkerSizeMultiplier() {
        if (workerData.getPosition() == null) return 1;
        return 1 + (float) workerData.getPosition().value / Position.values().length;
    }

    private void setCurrentPosition(Point state, boolean running) {
        currentPosition = state;
        redrawRequest.run();
    }

    private void setOpacity(Double opacity, boolean running) {
        this.opacity = Math.max(0, Math.min(opacity, 1));
        redrawRequest.run();
        if (!running && Math.abs(opacity) < 0.001 && endHandler != null) {
            transparencyAnimator.removeListener(this::setOpacity);
            SwingUtilities.invokeLater(endHandler);
        }
    }

    public void showObject() {
        transparencyAnimator.animate(1d);
    }

    public void hideObject(Runnable onEnd) {
        transparencyAnimator.animate(0d);
        this.endHandler = onEnd;
    }

    public void setOnRedrawRequest(Runnable target) {
        this.redrawRequest = target;
    }

    @Override
    public Integer getPrimaryKey() {
        return workerID;
    }
}
