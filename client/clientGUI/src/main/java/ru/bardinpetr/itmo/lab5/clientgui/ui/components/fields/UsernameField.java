package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractTextWorkerField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.CredentialsValidator;

import java.util.function.Consumer;

public class UsernameField extends AbstractTextWorkerField<String> {

    public UsernameField(Consumer<String> handler) {
        super(
                "UsernameField.toolText.text",
                (new CredentialsValidator())::validateUsername,
                handler
        );
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
