package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractEnumCombobox<T> extends AbstractWorkerComboBox<T> {
    public AbstractEnumCombobox(Consumer<T> handler) {
        super(handler);
    }


    @Override
    protected void groupItems() {
        for (var i: getList()){
            String text;
            if (i!=null)
                text = bundle.getString(
                        "AbstractEnumCombobox.value.%s.text".formatted(i.toString().toLowerCase())
                );
            else
                text = bundle.getString(
                        "AbstractEnumCombobox.value.null.text"
                );
            positionMap.put(text, i);
            addItem(text);
        }
    }



    private String getPosText(T pos){
        for( var i:positionMap.entrySet()){
            if (i.getValue() == pos) return i.getKey();
        }
        return "";
    }
    @Override
    public DataContainer<T> getData() {
        return new DataContainer(true,
                positionMap.get(((String) getSelectedItem())),
                ""
        );
    }

    @Override
    public void setData(T pos){
        setSelectedItem(
                getPosText(pos)
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
