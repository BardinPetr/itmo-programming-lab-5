package ru.bardinpetr.itmo.lab5.models.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Comparator;

@Data
@AllArgsConstructor
public class Organization implements Comparable<Organization> {
    @NonNull String fullName;
    @NonNull OrganizationType type;

    public Organization() {
    }

    @Override
    public int compareTo(Organization org) {
        Comparator<Organization> comparator = Comparator
                .comparing(Organization::getType)
                .thenComparing(Organization::getFullName);
        return comparator.compare(this, org);
    }
}
