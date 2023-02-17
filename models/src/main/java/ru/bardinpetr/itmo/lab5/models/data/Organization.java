package ru.bardinpetr.itmo.lab5.models.data;

import lombok.Data;
import lombok.NonNull;

//@Data
@Data
public class Organization implements Comparable<Organization> {
    @NonNull String fullName;
    @NonNull OrganizationType type;

    public Organization() {
    }

    @Override
    public int compareTo(Organization org) {
        if (type.getValue() != org.type.getValue())
            return type.getValue() - org.type.getValue();
        else
            return fullName.compareTo(org.fullName);
    }
}
