package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractTextWorkerField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.CoordinatesStringValidator;

import javax.swing.text.BadLocationException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;

public class XCoordinateWorkerField extends AbstractTextWorkerField<Integer> {
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

    protected String getFullText() {
        var format = NumberFormat.getNumberInstance(Locale.getDefault());
        var doc = getDocument();
        try {
            return format.format(
                    Integer.valueOf(doc.getText(0, doc.getLength()))
            );
        } catch (BadLocationException e) {
            throw null;
        }
    }
    @Override
    public void setData(Integer data) {
        setTextLater(String.valueOf(data));
    }
}
