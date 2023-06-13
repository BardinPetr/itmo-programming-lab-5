package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields;

import ru.bardinpetr.itmo.lab5.clientgui.models.factory.ModelProvider;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.AbstractWorkerComboBox;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.OrganizationPresenter;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class OrganizationCombobox extends AbstractWorkerComboBox<OrganizationPresenter> {
    public OrganizationCombobox(Consumer<OrganizationPresenter> handler) {
        super(handler);
    }

    protected List<OrganizationPresenter> getList() {
//        cachedList = new ArrayList<>(){{
//            APICommandMenger.getInstance().sendCommand(
//                    new GetOrgsCommand(),
//                    getParent(),
//                    "OrganizationIdBox.getIds.error",
//                    (e) -> {
//                        var orgs = ((GetOrgsCommand.OrganisationCommandResponse) e).organizations;
//                        for (var i: orgs){
//                            add(new OrganizationPresenter(i));
//                        }
//                    },
//                    true
//            );
//        }};
        cachedList = ModelProvider.getInstance().organizations().asList().stream().map(OrganizationPresenter::new).toList();
        return cachedList;

    }

    @Override
    protected void groupItems() {
        addItem(null);
        for (var i : getList()) {
            addItem(i);
        }
    }

    @Override
    public ValidationResponse validateValue() {
        var item = (OrganizationPresenter) getSelectedItem();
        if (item == null || getList().contains(item)) {
            setBackground(Color.WHITE);
            return new ValidationResponse(
                    true,
                    "");
        } else {
            initComponentsI18n();
            setBackground(Color.ORANGE);//TODO check null??
            setToolTipText(resources.get("OrganizationIdBox.previousValue.notFound.text"));
            return new ValidationResponse(
                    false,
                    "OrganizationIdBox.previousValue.notFound.text");
        }
    }

    @Override
    public DataContainer<OrganizationPresenter> getData() {
        var res = (OrganizationPresenter) getSelectedItem();
        if (res == null)
            return new DataContainer<>(null, validateValue());
        return new DataContainer<>(new OrganizationPresenter(res.getOrganization()), validateValue());

    }    private List<OrganizationPresenter> cachedList = getList();

    @Override
    public void setData(OrganizationPresenter data) {
        if (!cachedList.contains(data))
            addItem(data);
        setSelectedItem(data);
    }




}
