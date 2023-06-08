package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields;

import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields.interfaces.AbstractDateField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;

import java.util.Date;
import java.util.function.Consumer;

public class DateField extends AbstractDateField<Date> {
    public DateField(Consumer<Date> handler) {
        super(handler);
    }

    @Override
    public DataContainer<Date> getData() {

        return new DataContainer<>(
                true,
                (Date) getModel().getValue(),
                ""
        );
    }

    @Override
    public void setData(@NonNull Date data) {
        getModel().setValue(data);
    }
}
