package ru.bardinpetr.itmo.lab5.clientgui.ui.components.map;

import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

import javax.swing.*;
import java.awt.*;

public abstract class MapSprite extends JPanel implements IKeyedEntity<Integer> {
    public abstract Rectangle calculateBorder();
}
