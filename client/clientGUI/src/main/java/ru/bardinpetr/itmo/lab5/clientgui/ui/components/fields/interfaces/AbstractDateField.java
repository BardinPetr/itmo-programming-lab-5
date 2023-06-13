package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import javax.swing.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.function.Consumer;

public abstract class AbstractDateField<T> extends JSpinner implements IDataStorage<T> {
    protected UIResources resources;
    private DateFormat dateFormatter;


    public AbstractDateField(Consumer<Date> handler) {
        super(new SpinnerDateModel(
                )
        );

        getModel().addChangeListener((e) -> {
            var startDate = ((SpinnerDateModel) e.getSource()).getDate();
            handler.accept(startDate);
        });

        UIResources.getInstance().addLocaleChangeListener((i) -> initComponentsI18n());
        initComponentsI18n();

    }

    public void setEditable(boolean editable) {
        ((DefaultEditor) getEditor()).getTextField().setEditable(editable);
    }
    @Override
    public ValidationResponse validateValue() {
        return new ValidationResponse(true, "");
    }

    private UIResources getResources() {
        return UIResources.getInstance();
    }

    protected abstract String getStringFormatKey();
    protected void initComponentsI18n() {
        resources = getResources();

        dateFormatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.getDefault());
        var dateFormat = resources.get(getStringFormatKey());
        setToolTipText(
                resources.get("AbstractDateField.toolText.format") +
                        resources.get("dateFormat")
        );
        setEditor(new JSpinner.DateEditor(this, dateFormat));
    }


    public String getText() {
        return ((DateEditor) getEditor()).getFormat().format(getValue());
    }
}
