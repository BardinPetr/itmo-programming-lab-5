package ru.bardinpetr.itmo.lab5.clientgui.ui.components.map;

import ru.bardinpetr.itmo.lab5.clientgui.models.sync.ExternalSyncedListModel;
import ru.bardinpetr.itmo.lab5.clientgui.utils.GraphicsUtils;
import ru.bardinpetr.itmo.lab5.events.models.Event;
import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.HashMap;
import java.util.Map;


/**
 * @param <T> model object
 * @param <M> model type
 * @param <S> object renderer
 */
public abstract class MapPage<T extends IKeyedEntity<Integer>, M extends ExternalSyncedListModel<T>, S extends MapSprite> extends JPanel implements MouseMotionListener, MouseWheelListener, MouseListener, ListDataListener {

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

        recalculateAxis();

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                System.out.println(model.asList());
//                repaint();
            }
        });
    }

    public void start() {
        model.addListDataListener(this);
        model.addServerEventListener(this::onServerEvent);

        if (model.size() > 0)
            intervalAdded(new ListDataEvent(model, ListDataEvent.CONTENTS_CHANGED, 0, model.size() - 1));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2 = (Graphics2D) g;

        g2.setTransform(paneTransform);

        drawItems(g2);
        drawAxes(g2);
    }

    private void drawAxes(Graphics2D g) {
        var defaultStroke = g.getStroke();

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
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

        g.setStroke(defaultStroke);
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

    protected void recalculateAxis() {
        setAxis(new Point(700, 700), new Point(-700, -700));
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
        var posPane = e.getPoint();
        var pos = new Point();
        try {
            paneTransform.inverseTransform(posPane, pos);
        } catch (NoninvertibleTransformException ex) {
            return;
        }

        var sprite =
                sprites
                        .values()
                        .stream()
                        .filter(i -> i.calculateBorder().contains(pos))
                        .findFirst();
        if (sprite.isEmpty())
            return;

        var selectedId = sprite.get().getPrimaryKey();
        onClick(model.getByPK(selectedId));
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

    protected abstract S createSprite(Integer pk, T data);

    protected abstract void updateSprite(Integer pk, S sprite, T newData);

    @Override
    public void intervalAdded(ListDataEvent e) {
        for (var i = e.getIndex0(); i <= e.getIndex1(); i++) {
            var real = model.getElementAt(i);
            var pk = real.getPrimaryKey();
            sprites.put(
                    pk,
                    createSprite(pk, real)
            );
        }
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
//        for (var i = e.getIndex0(); i <= e.getIndex1(); i++) {
//            var real = model.getElementAt(i);
//            sprites.remove(real.getPrimaryKey(), null);
//        }
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        for (var i = e.getIndex0(); i <= e.getIndex1(); i++) {
            var real = model.getElementAt(i);

            var pk = real.getPrimaryKey();

            var sprite = sprites.getOrDefault(pk, null);
            if (sprite == null) {
                intervalAdded(new ListDataEvent(e.getSource(), e.getType(), i, i));
            } else {
                updateSprite(pk, sprite, real);
            }
        }
    }

    private void onServerEvent(Event event) {
        if (event.getAction() == Event.EventType.DELETE) {
            sprites.remove((Integer) event.getObject());
        }
    }

    abstract protected void onClick(T object);
}
