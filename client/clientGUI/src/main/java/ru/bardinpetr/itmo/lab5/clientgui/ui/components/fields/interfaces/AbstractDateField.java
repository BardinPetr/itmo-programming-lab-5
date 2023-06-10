package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public abstract class AbstractDateField<T> extends JSpinner implements IDataStorage<T> {
    protected ResourceBundle bundle;
    public AbstractDateField(Consumer<Date> handler) {
        super(new SpinnerDateModel(
                new Date(),
                null,
                null,
                Calendar.MONTH)
        );

        getModel().addChangeListener((e) -> {
            var startDate = ((SpinnerDateModel) e.getSource()).getDate();
            handler.accept(startDate);
        });

        UIResources.getInstance().addLocaleChangeListener((i) -> initComponentsI18n());
        initComponentsI18n();

    }
    public void setEditable(boolean editable){
        ((DefaultEditor) getEditor()).getTextField().setEditable(editable);
    };

    @Override
    public ValidationResponse validateValue(){
        return new ValidationResponse(true, "");
    }
    private ResourceBundle getResources(){
        return UIResources.getInstance().getBundle();
    }
    protected void initComponentsI18n() {
        bundle = getResources();
        var dateFormat = bundle.getString("dateFormat");
        setToolTipText(
                bundle.getString("AbstractDateField.toolText.format")+
                bundle.getString("dateFormat"));
        setEditor(new JSpinner.DateEditor(this, dateFormat));

    }

//    public String getText() {
//        return getValue();
//    }
}
