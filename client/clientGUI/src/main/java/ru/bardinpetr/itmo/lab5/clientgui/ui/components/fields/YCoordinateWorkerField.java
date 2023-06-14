package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractFormattedField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractTextWorkerField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.FloatTextField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.CoordinatesStringValidator;

import javax.swing.text.BadLocationException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;


public class YCoordinateWorkerField extends FloatTextField {
    public YCoordinateWorkerField(Consumer<String> handler) {
        super(
                "WorkerInfoPanel.yCoordinate.null.text",
                (new CoordinatesStringValidator())::validateY,
                handler
        );
    }
}