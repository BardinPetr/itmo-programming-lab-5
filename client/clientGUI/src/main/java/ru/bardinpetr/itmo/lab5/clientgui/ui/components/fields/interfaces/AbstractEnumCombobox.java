package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import java.util.function.Consumer;

public abstract class AbstractEnumCombobox<T> extends AbstractWorkerComboBox<T> {
    public AbstractEnumCombobox(Consumer<T> handler) {

        super(handler);
    }


    @Override
    protected void groupItems() {
        for (var i: getList()){
            addItem(i);
        }
    }

    @Override
    public DataContainer<T> getData() {
        return new DataContainer(true,
                getSelectedItem(),
                ""
        );
    }

    @Override
    public void setData(T pos){
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
