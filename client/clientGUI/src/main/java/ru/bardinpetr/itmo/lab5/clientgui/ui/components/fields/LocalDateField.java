package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractDateField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Consumer;

public class LocalDateField extends AbstractDateField<LocalDate> {
    public LocalDateField(Consumer<Date> handler) {
        super(handler);
    }
    @Override
    public DataContainer getData() {
        var date = (Date) getModel().getValue();
        return new DataContainer(
                true,
                date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                "");
    }
    @Override
    public void setData(LocalDate data) {
        var instant = data.atStartOfDay(ZoneId.systemDefault()).toInstant();
        getModel().setValue(Date.from(instant));
    }
}

