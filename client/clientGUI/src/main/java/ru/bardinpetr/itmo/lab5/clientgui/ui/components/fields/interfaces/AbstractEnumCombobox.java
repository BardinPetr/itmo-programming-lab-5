package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.EnumPresenter;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import java.util.function.Consumer;

public abstract class AbstractEnumCombobox extends AbstractWorkerComboBox<EnumPresenter> {
    public AbstractEnumCombobox(Consumer<EnumPresenter> handler) {
        super(handler);
    }


    @Override
    protected void groupItems() {
        for (var i: getList()){
            addItem(i);
        }
    }

    @Override
    public DataContainer<EnumPresenter> getData() {
        return new DataContainer(true,
                getSelectedItem(),
                ""
        );
    }


    @Override
    public void setData(EnumPresenter pos){
        setSelectedItem(
                pos
        );
    }

    @Override
    public ValidationResponse validateValue() {
        return new ValidationResponse(
                true,
                ""
        );
    }

}
