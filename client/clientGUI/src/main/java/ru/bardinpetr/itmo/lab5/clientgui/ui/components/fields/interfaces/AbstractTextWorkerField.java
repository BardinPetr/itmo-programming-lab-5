package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.IStringValidator;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.TextFieldValidator;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.function.Consumer;

import static javax.swing.SwingUtilities.invokeLater;

public abstract class AbstractTextWorkerField<T> extends JPasswordField implements IDataStorage<T> {
    public String toolTipMsg;
    protected UIResources resources;
    IStringValidator validator;

    public AbstractTextWorkerField(String toolTipMsg, IStringValidator validator, Consumer<String> handler) {
        super();
        this.validator = validator;
        this.toolTipMsg = toolTipMsg;
        setEchoChar('\u0000');
        UIResources.getInstance().addLocaleChangeListener((i) -> initComponentsI18n());

        getDocument().addDocumentListener(
                new TextFieldValidator(
                        validator,
                        (str) -> {
                            setBackground(Color.WHITE);
                            setToolTipText(null);
                            handler.accept(str);
                        },
                        (s) -> {
                            resources = getResources();
                            setBackground(Color.PINK);
                            setToolTipText(resources.get(s));
                        }
                )
        );
        UIResources.getInstance().addLocaleChangeListener((i) -> initComponentsI18n());
    }

    @Override
    public String getText() {
        return getFullText();
    }

    public ValidationResponse validateValue() {
        var validation = validator.validate(getFullText());
        if (!validation.isAllowed()) setBackground(Color.PINK);
        return validation;
    }

    protected String getFullText() {
        var doc = getDocument();
        try {
            return doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
            throw null;
        }

    }

    private UIResources getResources() {
        return UIResources.getInstance();
    }

    protected void initComponentsI18n() {
        resources = getResources();
        setToolTipText(resources.get(toolTipMsg));
    }

    protected void setTextLater(String text) {
        invokeLater(() -> setText(text));
    }

}
