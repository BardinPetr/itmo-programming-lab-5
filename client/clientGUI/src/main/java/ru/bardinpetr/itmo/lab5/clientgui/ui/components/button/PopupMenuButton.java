package ru.bardinpetr.itmo.lab5.clientgui.ui.components.button;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupMenuButton extends JToggleButton implements PopupMenuListener, ActionListener {

    private final JPopupMenu menu;

    public PopupMenuButton(JPopupMenu menu) {
        this.menu = menu;

        addActionListener(this);
        menu.addPopupMenuListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isSelected())
            menu.show(this, 0, getHeight());
        else
            menu.setVisible(false);
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        setSelected(false);
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) {
        setSelected(false);
    }
}