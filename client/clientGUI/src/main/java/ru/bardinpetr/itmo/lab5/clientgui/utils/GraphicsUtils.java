package ru.bardinpetr.itmo.lab5.clientgui.utils;

import java.awt.*;

public class GraphicsUtils {
    static public void drawArrow(Graphics g, int x0, int y0, int x1, int y1, int headLength) {
        double offs = 0.2 * Math.PI;
        double angle = Math.atan2(y0 - y1, x0 - x1);
        int[] xs = {x1 + (int) (headLength * Math.cos(angle + offs)), x1, x1 + (int) (headLength * Math.cos(angle - offs))};
        int[] ys = {y1 + (int) (headLength * Math.sin(angle + offs)), y1, y1 + (int) (headLength * Math.sin(angle - offs))};
        g.drawLine(x0, y0, x1, y1);
        g.drawPolyline(xs, ys, 3);
    }
}
