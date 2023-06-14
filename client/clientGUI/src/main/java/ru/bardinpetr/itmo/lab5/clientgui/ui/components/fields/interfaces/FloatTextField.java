package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.IStringValidator;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class FloatTextField extends AbstractFormattedField<Float> {


    public FloatTextField(String toolTipMsg, IStringValidator validator, Consumer<String> handler) {
        super(toolTipMsg, validator, handler);
    }

    @Override
    public DataContainer<Float> getData() {
        var validation = validateValue();
        if (validation.isAllowed())
            return new DataContainer<>(
                    Float.parseFloat(getFullText()),
                    validateValue());
        else
            return new DataContainer<>(
                    0.0f,
                    validateValue());
    }

    @Override
    public void setData(Float data) {
        setTextLater(new BigDecimal(data).toPlainString());
    }
}
