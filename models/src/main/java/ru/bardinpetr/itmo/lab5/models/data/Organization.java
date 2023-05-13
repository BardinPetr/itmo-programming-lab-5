package ru.bardinpetr.itmo.lab5.models.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.data.annotation.FieldValidator;
import ru.bardinpetr.itmo.lab5.models.data.annotation.InteractText;
import ru.bardinpetr.itmo.lab5.models.data.validation.OrganizationValidator;

import java.util.Comparator;

@Data
@AllArgsConstructor
public class Organization implements Comparable<Organization> {
    @NonNull
    @InteractText("Enter org id")
    Integer id;

    @NonNull
    @InteractText("Enter an organization name")
    @FieldValidator(OrganizationValidator.class)
    String fullName;

    @NonNull
    @InteractText("""
            Enter organisation type from list:
                   COMMERCIAL,
                   PUBLIC,
                   PRIVATE_LIMITED_COMPANY,
                   OPEN_JOINT_STOCK_COMPANY""")
    @FieldValidator(OrganizationValidator.class)
    OrganizationType type;

    public Organization() {
    }

    public static Comparator<Organization> getComparator() {
        return Comparator
                .comparing(Organization::getType)
                .thenComparing(Organization::getFullName);
    }

    @Override
    public int compareTo(Organization org) {
        return getComparator().compare(this, org);
    }
}
