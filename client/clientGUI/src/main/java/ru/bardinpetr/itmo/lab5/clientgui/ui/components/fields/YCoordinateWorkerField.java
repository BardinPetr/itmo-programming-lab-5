package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractFormattedField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractTextWorkerField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.CoordinatesStringValidator;

import javax.swing.text.BadLocationException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;


public class YCoordinateWorkerField extends AbstractFormattedField<Float> {
    public YCoordinateWorkerField(Consumer<String> handler) {
        super(
                "WorkerInfoPanel.yCoordinate.null.text",
                (new CoordinatesStringValidator())::validateY,
                handler
        );
    }

    @Override
    public DataContainer<Float> getData() {
        var validation = validateValue();
        if (validation.isAllowed())
            return new DataContainer(
                    Float.parseFloat(getFullText()),
                    validateValue());
        else
            return new DataContainer(
                    0.0f,
                    validateValue());
    }

    @Override
    public void setData(Float data) {
         setTextLater(String.valueOf(data));
    }

    @Override
    protected Float convert(String value) {
        return Float.valueOf(value);
    }
}