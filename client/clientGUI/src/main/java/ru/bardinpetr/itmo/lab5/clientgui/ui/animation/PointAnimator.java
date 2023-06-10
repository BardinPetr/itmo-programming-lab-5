package ru.bardinpetr.itmo.lab5.clientgui.ui.animation;

import lombok.Setter;

import java.awt.*;

public class PointAnimator extends PropertyAnimator<Point> {
    private static final int PERIOD = 50;
    private final double speed;
    private double yStep = 0;
    @Setter
    private double distanceEpsilon = 5;
    private double xStep = 0;

    public PointAnimator(double pxPerSec) {
        super(PERIOD);
        speed = pxPerSec;
    }

    @Override
    public void animate(Point start, Point end) {
        double baseDist = startPoint.distance(endPoint);
        double predictedTime = baseDist / speed;
        double steps = predictedTime * 1000 / PERIOD;
        xStep = (end.x - start.x) / steps;
        yStep = (end.y - start.y) / steps;

        super.animate(start, end);
    }

    @Override
    protected boolean checkEnded(Point currentStatus) {
        return currentStatus.distance(endPoint) < distanceEpsilon;
    }

    @Override
    protected Point update(Point cur) {
        return new Point((int) (cur.x + xStep), (int) (cur.y + yStep));
    }
}