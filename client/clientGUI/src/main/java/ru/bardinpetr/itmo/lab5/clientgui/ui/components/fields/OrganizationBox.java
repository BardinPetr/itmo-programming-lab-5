package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractWorkerComboBox;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetOrgsCommand;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class OrganizationBox extends AbstractWorkerComboBox<Organization> {
    private List<Organization> cachedList = getList();
    public OrganizationBox(Consumer<Organization> handler) {
        super(handler);
    }
    protected List<Organization> getList(){
        cachedList = new ArrayList<>(){{
            new APICommandMenger().sendCommand(
                    new GetOrgsCommand(),
                    getParent(),
                    "OrganizationIdBox.getIds.error",
                    (e) -> {
                        var orgs = ((GetOrgsCommand.OrganisationCommandResponse) e).organizations;
                        addAll(orgs);
                    }
            );
        }};
        return cachedList;

    }


    @Override
    protected void groupItems() {
        addItem(null);
        for (var i: getList()){
            addItem(i);
        }
    }
    @Override
    public void setData(Organization data) {
        if (data==null)
            setSelectedItem("");
        else{
            if (!cachedList.contains(data))
                addItem(data);
            setSelectedItem(data);
        }
    }


    @Override
    public ValidationResponse validateValue(){
        var item = (Organization) getSelectedItem();
        if (item==null||getList().contains(item)) {
            setBackground(Color.WHITE);
            return new ValidationResponse(
                    true,
                    "");
        }
        else {
            initComponentsI18n();
            setBackground(Color.ORANGE);//TODO check null??
            setToolTipText(bundle.getString("OrganizationIdBox.previousValue.notFound.text"));
            return new ValidationResponse(
                    false,
                    "OrganizationIdBox.previousValue.notFound.text");
        }
    }
    @Override
    public DataContainer<Organization> getData() {
        return new DataContainer<>((Organization) getSelectedItem(), validateValue());

    }



}
