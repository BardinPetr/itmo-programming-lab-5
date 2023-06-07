package ru.bardinpetr.itmo.lab5.clientgui.ui.components.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IconButton extends JButton {
    public IconButton(int iconSize) {
        var dim = new Dimension(iconSize, iconSize);

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setPreferredSize(dim);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fireActionPerformed(new ActionEvent(e.getSource(), 0, ""));
            }
        });
    }
}
