package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractFormattedField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractTextWorkerField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.CoordinatesStringValidator;

import javax.swing.text.BadLocationException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;

public class XCoordinateWorkerField extends AbstractFormattedField<Integer> {
    public XCoordinateWorkerField(Consumer<String> handler) {
        super(
                "WorkerInfoPanel.xCoordinate.null.text",
                (new CoordinatesStringValidator())::validateX,
                handler
        );
    }

    @Override
    public DataContainer<Integer> getData() {
        var validation = validateValue();
        if (validation.isAllowed())
            return new DataContainer<>(
                    Integer.parseInt(getFullText()),
                    validateValue());
        else
            return new DataContainer<>(
                    0,
                    validateValue());

    }


    @Override
    public void setData(Integer data) {
        setTextLater(new BigDecimal(data).toPlainString());
    }
}
