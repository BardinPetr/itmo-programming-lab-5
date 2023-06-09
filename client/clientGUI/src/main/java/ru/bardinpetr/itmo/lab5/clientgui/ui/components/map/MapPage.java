package ru.bardinpetr.itmo.lab5.clientgui.ui.components.map;

import ru.bardinpetr.itmo.lab5.clientgui.models.ExtendedListModel;
import ru.bardinpetr.itmo.lab5.clientgui.utils.GraphicsUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;


/**
 * @param <T> model object
 * @param <M> model type
 * @param <S> object renderer
 */
public class MapPage<T, M extends ExtendedListModel<T>, S extends JPanel> extends JPanel implements MouseMotionListener, MouseWheelListener, MouseListener {

    private static final double SCALE_FACTOR = 0.1;
    private static final int AXES_TICK_STEP = 50;
    private static final int AXES_TICK_SIZE = 10;
    protected final M model;
    protected final Map<Integer, S> sprites = new HashMap<>();
    private final AffineTransform paneTransform = new AffineTransform();
    private Point paneMaxCoords;
    private Point paneMinCoords;
    private Point dragLastPosition = new Point(0, 0);

    public MapPage(M model) {
        this.model = model;

        paneMaxCoords = new Point(700, 700);
        paneMinCoords = new Point(-700, -700);

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

//        model.addListDataListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2 = (Graphics2D) g;

        g2.setTransform(paneTransform);

        drawAxes(g2);
        drawItems(g2);
    }

    private void drawAxes(Graphics2D g) {
        g.setColor(Color.BLACK);
        GraphicsUtils.drawArrow(g, paneMinCoords.x, 0, paneMaxCoords.x, 0, 20);
        GraphicsUtils.drawArrow(g, 0, paneMaxCoords.y, 0, paneMinCoords.y, 20);

        var frc = g.getFontRenderContext();
        var font = g.getFont();

        int labelShift = (int) (AXES_TICK_SIZE * 2.5);
        int x = paneMinCoords.x - (paneMinCoords.x % AXES_TICK_STEP);
        int y = paneMinCoords.y - (paneMinCoords.y % AXES_TICK_STEP);

        for (; x < paneMaxCoords.x; x += AXES_TICK_STEP) {
            if (x == 0) continue;
            g.drawLine(x, -AXES_TICK_SIZE, x, AXES_TICK_SIZE);
            var str = String.valueOf(x);
            var bounds = font.getStringBounds(str, frc);
            g.drawString(str, x - (int) bounds.getWidth() / 2, labelShift);
        }
        for (; y < paneMaxCoords.y; y += AXES_TICK_STEP) {
            if (y == 0) continue;
            g.drawLine(-AXES_TICK_SIZE, y, AXES_TICK_SIZE, y);
            var str = String.valueOf(-y);
            var bounds = font.getStringBounds(str, frc);
            g.drawString(str, labelShift, y + (int) bounds.getHeight() / 2);
        }
    }

    public void centerMap() {
        paneTransform.setToTranslation(getVisibleRect().x / 2f, getVisibleRect().y / 2f);
    }

    private void drawItems(Graphics2D g) {
        for (var i : sprites.values())
            i.paint(g);
    }

    public void setAxis(Point minCoords, Point maxCoords) {
        paneMinCoords = minCoords;
        paneMaxCoords = maxCoords;
        repaint();
    }

    private void movePane(int dx, int dy) {
        SwingUtilities.invokeLater(() -> {
            if (dx > 100 || dy > 100)
                return;
            paneTransform.translate(dx, dy);
            repaint();
        });
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        movePane(e.getX() - dragLastPosition.x, e.getY() - dragLastPosition.y);
        dragLastPosition = e.getPoint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        SwingUtilities.invokeLater(() -> {
            var delta = 1 + -SCALE_FACTOR * e.getPreciseWheelRotation();
            paneTransform.scale(delta, delta);
            repaint();
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        dragLastPosition = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
