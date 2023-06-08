package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields.interfaces.AbstractTextWorkerField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.CoordinatesStringValidator;

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
            return new DataContainer<Integer>(
                    Integer.parseInt(getFullText()),
                    validateValue());
        else
            return new DataContainer<Integer>(
                    0,
                    validateValue());

    }
    @Override
    public void setData(Integer data) {
        setTextLater(String.valueOf(data));
    }
}
