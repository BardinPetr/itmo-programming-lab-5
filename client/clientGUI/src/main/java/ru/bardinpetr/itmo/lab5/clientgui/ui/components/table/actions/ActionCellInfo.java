package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.actions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ActionCellInfo extends ArrayList<ActionCellInfo.ActionCellControl> {
    public static ActionCellInfo of(ActionCellControl... controls) {
        var res = new ActionCellInfo();
        res.addAll(List.of(controls));
        return res;
    }

    public record ActionCellControl(JComponent component) {
    }
}
