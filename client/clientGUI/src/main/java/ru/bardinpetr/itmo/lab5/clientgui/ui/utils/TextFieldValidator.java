package ru.bardinpetr.itmo.lab5.clientgui.ui.utils;

import ru.bardinpetr.itmo.lab5.models.validation.IValidator;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.util.function.Consumer;

public class TextFieldValidator implements DocumentListener  {
    private IStringValidator validator;
    private Consumer<String> good;
    private Consumer<String> bad;
    public TextFieldValidator(IStringValidator validator, Consumer<String> good, Consumer<String> bad) {
        this.validator = validator;
        this.good = good;
        this.bad = bad;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        change(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        change(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        change(e);
    }
    private void change(DocumentEvent e){
        var doc = e.getDocument();
        String text;
        try {
            text = doc.getText(0, doc.getLength());
        } catch (BadLocationException ee) {
            return;
        }

        var resp = validator.validate(text);
        if (resp.isAllowed()){
            good.accept(text);
        }
        else {
            bad.accept(resp.getMsg());
        }
    }
}
