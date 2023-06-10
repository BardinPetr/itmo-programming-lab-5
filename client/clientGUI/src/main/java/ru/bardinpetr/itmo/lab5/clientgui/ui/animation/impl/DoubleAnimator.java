package ru.bardinpetr.itmo.lab5.clientgui.ui.animation.impl;

import lombok.Setter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.animation.PropertyAnimator;

public class DoubleAnimator extends PropertyAnimator<Double> {
    private static final int PERIOD = 25;
    private final double speed;
    private double step;
    @Setter
    private double epsilon = 0.001;


    public DoubleAnimator(double deltaPerMs) {
        super(PERIOD);
        this.speed = deltaPerMs;
    }

    @Override
    protected boolean checkEnded(Double currentStatus) {
        return Math.abs(currentStatus - endPoint) < epsilon;
    }

    @Override
    protected void preconfigure() {
        step = (endPoint < startPoint ? -1d : 1d) * speed * PERIOD;
    }

    @Override
    protected Double update(Double cur) {
        return cur + step;
    }
}