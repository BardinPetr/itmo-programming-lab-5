package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields.interfaces.AbstractTextWorkerField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.CoordinatesStringValidator;

import java.util.function.Consumer;


public class YCoordinateWorkerField extends AbstractTextWorkerField<Float> {
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
}