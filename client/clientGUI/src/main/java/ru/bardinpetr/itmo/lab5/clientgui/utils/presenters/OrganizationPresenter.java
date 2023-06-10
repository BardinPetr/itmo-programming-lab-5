package ru.bardinpetr.itmo.lab5.clientgui.utils.presenters;

import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.OrganizationType;

import java.util.Objects;

public class OrganizationPresenter {
    private Organization organization;
    public OrganizationPresenter(Integer id, String fullName, OrganizationType type) {
        organization = new Organization(id, fullName, type);
    }

    public OrganizationPresenter() {
        organization = new Organization();
    }

    public Organization getOrganization(){
        return organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationPresenter that = (OrganizationPresenter) o;

        return Objects.equals(organization.getId(), that.organization.getId());
    }



    @Override
    public String toString() {
        return "(%d) %s [%s]".formatted(
                organization.getId(),
                organization.getFullName(),
                new EnumPresenter<>(organization.getType()));
    }
    public static OrganizationPresenter fromOrganization(Organization source){
        if (source == null) return null;
        return new OrganizationPresenter(
                source.getId(),
                source.getFullName(),
                source.getType());
    }
}
