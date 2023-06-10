package ru.bardinpetr.itmo.lab5.clientgui.ui.animation.impl;

import lombok.Setter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.animation.PropertyAnimator;

import java.awt.*;

public class PointAnimator extends PropertyAnimator<Point> {
    private static final int PERIOD = 25;
    private final double speed;
    private double yVal = 0;
    private double xVal = 0;
    private double yStep = 0;
    private double xStep = 0;
    @Setter
    private double distanceEpsilon = 3;


    public PointAnimator(double pxPerSec) {
        super(PERIOD);
        speed = pxPerSec;
    }

    @Override
    protected void preconfigure() {
        double baseDist = startPoint.distance(endPoint);
        double predictedTime = baseDist / speed;
        double steps = Math.ceil(predictedTime * 1000 / PERIOD);
        if (((int) steps) == 0) {
            xStep = 0;
            yStep = 0;
            return;
        }
        xStep = (endPoint.x - startPoint.x) / steps;
        yStep = (endPoint.y - startPoint.y) / steps;
        xVal = startPoint.x;
        yVal = startPoint.y;
    }

    @Override
    protected boolean checkEnded(Point currentStatus) {
        return currentStatus.distance(endPoint) < distanceEpsilon;
    }

    @Override
    protected Point update(Point cur) {
        xVal += xStep;
        yVal += yStep;
        return new Point((int) xVal, (int) yVal);
    }
}