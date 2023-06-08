package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields.interfaces.AbstractWorkerComboBox;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OrganizationIdBox extends AbstractWorkerComboBox<Integer> {
    public OrganizationIdBox(Consumer<Integer> handler) {
        super(handler);
    }

    private List<Integer> getIds(){
        return new ArrayList<>(){{
            add(1);
            add(3);
        }};//TODO server connection
    }


    @Override
    protected void groupItems() {
        addItem("");
        for (var i: getIds()){
            positionMap.put(String.valueOf(i), i);
            addItem(String.valueOf(i));
        }
    }
    public ValidationResponse validateValue(){
        return new ValidationResponse(      //TODO get id list
                true,
                "");
    }
    @Override
    public DataContainer<Integer> getData() {
        var text = (String) getSelectedItem();
        if (text.isEmpty()) return new DataContainer<>(0, validateValue());
        return new DataContainer(
                Integer.parseInt(text),
                validateValue()
        );
    }

}
