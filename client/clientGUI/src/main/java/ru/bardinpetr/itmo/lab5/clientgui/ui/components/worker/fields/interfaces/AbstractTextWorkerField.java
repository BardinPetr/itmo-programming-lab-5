package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.IStringValidator;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.TextFieldValidator;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerStringValidation;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static javax.swing.SwingUtilities.invokeLater;

public abstract class AbstractTextWorkerField<T> extends JTextField implements IDataStorage<T>{
    private ResourceBundle bundle;
    public String toolTipMsg;
    IStringValidator validator;
    public AbstractTextWorkerField(String toolTipMsg, IStringValidator validator, Consumer<String> handler) {
        super();
        this.validator = validator;
        this.toolTipMsg = toolTipMsg;
//        setBackground(Color.PINK);
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
                            bundle = getResources();
                            setBackground(Color.PINK);
                            setToolTipText(bundle.getString(s));
                        }
                )
        );
    }

    public ValidationResponse validateValue(){
        return validator.validate(getFullText());
    }
    protected String getFullText(){
        var doc = getDocument();
        try {
            return doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
            throw null;
        }

    }

    private ResourceBundle getResources(){
        return UIResources.getInstance().getBundle();
    }
    protected void initComponentsI18n() {
        bundle = getResources();
        setToolTipText(bundle.getString(toolTipMsg));
    }

    protected void setTextLater(String text){
        invokeLater(() -> setText(text));
    }

}
