package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractDateField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Consumer;

public class LocalDateField extends AbstractDateField<LocalDate> {


    public LocalDateField(Consumer<Date> handler) {
        super(handler);
    }

    @Override
    protected String getStringFormatKey() {
        return "dateFormat";
    }


    @Override
    public DataContainer getData() {
        var date = (LocalDate) getModel().getValue();
        return new DataContainer(
                true,
                date,
                "");
    }
    @Override
    public void setData(LocalDate data) {
        var instant = data.atStartOfDay(ZoneId.systemDefault()).toInstant();
        getModel().setValue(Date.from(instant));
    }
}

