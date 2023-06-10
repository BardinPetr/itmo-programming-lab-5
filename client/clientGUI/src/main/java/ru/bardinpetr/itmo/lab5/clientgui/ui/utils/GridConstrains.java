package ru.bardinpetr.itmo.lab5.clientgui.ui.utils;

import java.awt.*;

public class GridConstrains {
    public static GridBagConstraints normalAdd(){
        return new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
                1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0,2,0,4), 0,0);
    }
    public static GridBagConstraints placedAdd(int x, int y){
        return placedAdd(x, y, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
    }
    public static GridBagConstraints placedAdd(int x, int y, int anchor, int fill){
        return new GridBagConstraints(x,y,
                1, 1, 1, 1,
                anchor, fill,
                new Insets(0,2,0,4), 0,0);
    }

}
