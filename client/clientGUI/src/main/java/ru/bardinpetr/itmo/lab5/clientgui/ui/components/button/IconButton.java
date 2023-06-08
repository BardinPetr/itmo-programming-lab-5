package ru.bardinpetr.itmo.lab5.clientgui.ui.components.button;

import javax.swing.*;
import java.awt.*;

public class IconButton extends JButton {
    public IconButton(int iconSize) {
        var dim = new Dimension(iconSize, iconSize);

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        setPreferredSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);
    }

    public IconButton(Icon icon) {
        this(icon.getIconHeight());
        setIcon(icon);
    }
}
