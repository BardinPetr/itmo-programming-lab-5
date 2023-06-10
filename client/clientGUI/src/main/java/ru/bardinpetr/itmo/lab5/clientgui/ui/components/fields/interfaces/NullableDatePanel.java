package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.LocalDateField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class NullableDatePanel extends JPanel implements IDataStorage<LocalDate> {
    private final LocalDateField workerEndDateField;
    private final JCheckBox endDateNullCheckbox;

    public NullableDatePanel(Consumer<Date> handler) {
        super(new GridBagLayout());

        workerEndDateField = new LocalDateField(handler);
        endDateNullCheckbox = new JCheckBox();

        var endDateFieldConstrain = GridConstrains.normalAdd();
        endDateFieldConstrain.fill = GridBagConstraints.HORIZONTAL;
        add(workerEndDateField, endDateFieldConstrain);
        add(endDateNullCheckbox);

        endDateNullCheckbox.setSelected(true);
        workerEndDateField.setEnabled(false);

        endDateNullCheckbox.addItemListener(this::changeEndDateNull);


        UIResources.getInstance().addLocaleChangeListener((i) -> initComponentsI18n());
        initComponentsI18n();
    }

    public void setEditable(boolean isEditable){
        workerEndDateField.setEditable(isEditable);
        endDateNullCheckbox.setEnabled(isEditable);
    }
    private void changeEndDateNull(ItemEvent e){
        workerEndDateField.setEnabled(e.getStateChange() != ItemEvent.SELECTED);
    }
    @Override
    public DataContainer<LocalDate> getData() {
        if (!endDateNullCheckbox.isSelected())
            return workerEndDateField.getData();
        else
            return new DataContainer<>(true,null,"");
    }

    @Override
    public void setData(LocalDate data) {
        if (data==null){
            endDateNullCheckbox.setSelected(true);
            workerEndDateField.setEnabled(false);
        }
        else{
            endDateNullCheckbox.setSelected(false);
            workerEndDateField.setEnabled(true);
            workerEndDateField.setData(data);
        }
    }

    @Override
    public ValidationResponse validateValue() {
        return workerEndDateField.validateValue();
    }

    private void initComponentsI18n() {
        ResourceBundle bundle = getResources();
        endDateNullCheckbox.setText(bundle.getString("WorkerInfoPanel.endDateNull.text"));
    }
    private ResourceBundle getResources(){
        return UIResources.getInstance().getBundle();
    }
}
