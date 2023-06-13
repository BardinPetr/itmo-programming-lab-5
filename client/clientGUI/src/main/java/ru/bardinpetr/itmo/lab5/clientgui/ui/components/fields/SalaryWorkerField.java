package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractTextWorkerField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerStringValidation;

import javax.swing.text.BadLocationException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;

public class SalaryWorkerField extends AbstractTextWorkerField<Float> {
    public SalaryWorkerField(Consumer<Float> handler) {
        super(
                "WorkerInfoPanel.workerSalary.null.text",
                (new WorkerStringValidation())::validateSalary,
                (s -> {handler.accept(Float.parseFloat(s));})

        );
    }

    protected String getFullText() {
        var format = NumberFormat.getNumberInstance(Locale.getDefault());
        var doc = getDocument();
        try {
            return format.format(
                    Float.valueOf(doc.getText(0, doc.getLength()))
            );
        } catch (BadLocationException e) {
            throw null;
        }
    }
    @Override
    public DataContainer<Float> getData() {
        var validation = validateValue();
        if (validation.isAllowed())
            return new DataContainer<Float>(
                    Float.parseFloat(getFullText()),
                    validateValue());
        else
            return new DataContainer<Float>(
                    0.0f,
                    validateValue());
    }

    @Override
    public void setData(Float data) {
        setTextLater(String.valueOf(data));
    }
}
