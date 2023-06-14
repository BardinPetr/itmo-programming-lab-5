package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractFormattedField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractTextWorkerField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.FloatTextField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerStringValidation;

import javax.swing.text.BadLocationException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;

public class SalaryWorkerField extends FloatTextField {
    public SalaryWorkerField(Consumer<Float> handler) {
        super(
                "WorkerInfoPanel.workerSalary.null.text",
                (new WorkerStringValidation())::validateSalary,
                (s -> {handler.accept(Float.parseFloat(s));})

        );
    }

}
