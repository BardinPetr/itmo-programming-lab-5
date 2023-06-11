package ru.bardinpetr.itmo.lab5.clientgui.utils.presenters;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.OrganizationType;

import java.util.Objects;

public class OrganizationPresenter implements Comparable<OrganizationPresenter>{
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
        if (organization==null)
            return UIResources.getInstance().get("WorkerInfoPanel.endDateNull.text");
        else
            return "(%d) %s [%s]".formatted(
                organization.getId(),
                organization.getFullName(),
                new EnumPresenter<>(organization.getType()));
    }
    public OrganizationPresenter(Organization source){
        if (source == null) organization = null;
        else {
            organization = new Organization(
                    source.getId(),
                    source.getFullName(),
                    source.getType()
            );
        }
    }

    @Override
    public int compareTo(OrganizationPresenter o) {
        if (o.organization==null) {
            if (organization==null) return 0;
            return 1;
        }
        if (organization==null) return -1;
        return organization.compareTo(o.organization);
    }
}
