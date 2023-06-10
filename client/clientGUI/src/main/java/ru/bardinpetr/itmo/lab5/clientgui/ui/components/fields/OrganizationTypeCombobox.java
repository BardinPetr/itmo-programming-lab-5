package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractEnumCombobox;
import ru.bardinpetr.itmo.lab5.models.data.OrganizationType;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class OrganizationTypeCombobox extends AbstractEnumCombobox<OrganizationType> {
    public OrganizationTypeCombobox(Consumer<OrganizationType> handler) {
        super(handler);
    }

    @Override
    protected List<OrganizationType> getList() {
        return Arrays.stream(OrganizationType.values()).toList();
    }

}
