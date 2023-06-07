package ru.bardinpetr.itmo.lab5.clientgui.ui.components.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.List;

public class PopupPanel extends JPanel {

    private final JComponent baseComponent;
    private final PopupFactory factory;
    private Popup currentPopup;

    public PopupPanel(JComponent trigger) {
        factory = PopupFactory.getSharedInstance();

        baseComponent = trigger;
        trigger.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentPopup == null)
                    open();
                else
                    close();
            }
        });

        // Close panel on clicked outside / focus lost
        Toolkit.getDefaultToolkit().addAWTEventListener(
                event -> {
                    if (List.of(FocusEvent.FOCUS_LOST, WindowEvent.WINDOW_LOST_FOCUS).contains(event.getID())) {
                        close();
                    }
                    if (event instanceof MouseEvent mEvent) {
                        var component = SwingUtilities.getDeepestComponentAt(this, mEvent.getX(), mEvent.getY());
                        if (event.getID() == MouseEvent.MOUSE_CLICKED && (component == null || !component.isValid()))
                            close();
                    }
                },
                AWTEvent.FOCUS_EVENT_MASK | AWTEvent.WINDOW_FOCUS_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK
        );
    }

    public void open() {
        var loc = baseComponent.getLocationOnScreen();
        currentPopup = factory.getPopup(
                baseComponent, this, loc.x, loc.y + baseComponent.getHeight()
        );

        currentPopup.show();

    }

    public void close() {
        if (currentPopup == null) return;
        currentPopup.hide();
        currentPopup = null;
    }

}
