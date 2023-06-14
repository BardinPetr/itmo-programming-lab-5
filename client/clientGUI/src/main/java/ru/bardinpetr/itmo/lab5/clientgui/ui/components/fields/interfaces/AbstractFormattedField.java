package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.IStringValidator;

import javax.swing.text.BadLocationException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;

public abstract class AbstractFormattedField<T> extends AbstractTextWorkerField<T>{
    public AbstractFormattedField(String toolTipMsg, IStringValidator validator, Consumer<String> handler) {
        super(toolTipMsg, validator, handler);
    }

    protected String getFullText() {
        var format = NumberFormat.getNumberInstance(Locale.getDefault());
        var doc = getDocument();
        try {
            var text = doc.getText(0, doc.getLength());
            if (text.isEmpty())
                return "";
            return format.format(
                    convert(text)
            );
        } catch (BadLocationException ignore) {
            return "";
        }
    }

    protected abstract T convert(String value);
}
