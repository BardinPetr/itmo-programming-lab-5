package data;

import data.validationExceptions.WrongDataException;
import org.jetbrains.annotations.NotNull;

public class Organization implements Comparable<Organization>{
    private String fullName; //Поле не может быть null
    private OrganizationType type; //Поле не может быть null

    public int compareTo(Organization org){
        if (type.getValue() != org.type.getValue()) return type.getValue() - org.type.getValue();
        else return fullName.compareTo(org.fullName);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(@NotNull String fullName) throws WrongDataException {
        this.fullName = fullName;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }
}
