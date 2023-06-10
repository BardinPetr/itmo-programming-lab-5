package ru.bardinpetr.itmo.lab5.clientgui.utils.presenters;

import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.OrganizationType;

public class OrganizationPresenter extends Organization {
    public OrganizationPresenter(Integer id, String fullName, OrganizationType type) {
        super(id, fullName, type);
    }

    public OrganizationPresenter() {
        super();
    }

    public Organization getOrganization(){
        return this;
    }

    @Override
    public String toString() {
        return "(%d) %s [%s]".formatted(getId(), getFullName(), new EnumPresenter<>(getType()));
    }
    public static OrganizationPresenter fromOrganization(Organization source){
        if (source == null) return null;
        return new OrganizationPresenter(
                source.getId(),
                source.getFullName(),
                source.getType());
    }
}
