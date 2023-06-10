package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractEnumCombobox;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.EnumPresenter;
import ru.bardinpetr.itmo.lab5.models.data.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PositionComboBox extends AbstractEnumCombobox {

    public PositionComboBox(Consumer<EnumPresenter> handler) {
        super(handler);
    }
    @Override
    protected List<EnumPresenter> getList() {
        var res = new ArrayList<EnumPresenter>();
        res.add(new EnumPresenter<>(null));
        res.addAll(
                EnumPresenter.fromList(
                        List.of(Position.values())
                )
        );
        return res;
    }



}
