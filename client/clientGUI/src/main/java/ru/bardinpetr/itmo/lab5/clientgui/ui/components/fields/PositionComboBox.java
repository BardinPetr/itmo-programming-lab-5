package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractEnumCombobox;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractWorkerComboBox;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.Position;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class PositionComboBox extends AbstractEnumCombobox<Position> {

    public PositionComboBox(Consumer<Position> handler) {
        super(handler);
    }
    @Override
    protected List<Position> getList() {
        ArrayList<Position> list = new ArrayList();
        list.add(null);
        list.addAll(Arrays.stream(Position.values()).toList());
        return list;
    }
}
