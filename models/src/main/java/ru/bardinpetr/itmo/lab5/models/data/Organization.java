package ru.bardinpetr.itmo.lab5.models.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class Organization implements Comparable<Organization> {
    @NonNull String fullName;
    @NonNull OrganizationType type;

    @Override
    public int compareTo(Organization org) {
        if (type.getValue() != org.type.getValue())
            return type.getValue() - org.type.getValue();
        else
            return fullName.compareTo(org.fullName);
    }
}
