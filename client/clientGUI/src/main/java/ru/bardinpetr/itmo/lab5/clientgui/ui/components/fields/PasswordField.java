package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractTextWorkerField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.CredentialsValidator;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerStringValidation;

import java.util.function.Consumer;

public class PasswordField extends AbstractTextWorkerField<String> {
    public PasswordField(Consumer<String> handler) {
        super(
                "PasswordField.toolText.text",
                (new CredentialsValidator())::validatePassword,
                handler
        );
        setEchoChar('*');
    }

    @Override
    public DataContainer<String> getData() {
        var validationResponse = validateValue();
        return new DataContainer(getFullText(), validationResponse);
    }

    @Override
    public void setData(String data) {
        setTextLater(data);
    }
}
