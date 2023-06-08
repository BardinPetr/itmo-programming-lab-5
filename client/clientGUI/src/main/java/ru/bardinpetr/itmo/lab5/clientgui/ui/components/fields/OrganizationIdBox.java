package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractWorkerComboBox;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OrganizationIdBox extends AbstractWorkerComboBox<Integer> {
    public OrganizationIdBox(Consumer<Integer> handler) {
        super(handler);
    }
    public String noSuchIdMsg = "OrganizationIdBox.validateValue.notFound.text";
    protected List<Integer> getList(){
        return new ArrayList<>(){{
            add(1);
            add(3);
        }};//TODO server connection
    }


    @Override
    protected void groupItems() {
        addItem("");
        for (var i: getList()){
            positionMap.put(String.valueOf(i), i);
            addItem(String.valueOf(i));
        }
    }
    @Override
    public void setData(Integer data) {
        if (data==null)
            setSelectedItem("");
        else{
            if (!getList().contains(data))
                addItem(String.valueOf(data));
            setSelectedItem(String.valueOf(data));
        }
    }
    public ValidationResponse validateValue(){
        var text = (String) getSelectedItem();
        if (text.isEmpty()||getList().contains(Integer.parseInt(text))) {
            setBackground(Color.WHITE);
            return new ValidationResponse(
                    true,
                    "");
        }
        else {
            initComponentsI18n();
            setBackground(Color.PINK);
            return new ValidationResponse(
                    false,
                    noSuchIdMsg);
        }
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
