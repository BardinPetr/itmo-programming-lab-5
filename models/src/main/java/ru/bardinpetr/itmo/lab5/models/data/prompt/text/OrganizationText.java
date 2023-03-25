package ru.bardinpetr.itmo.lab5.models.data.prompt.text;

public enum OrganizationText {
    ORGANISATIONNAMEINTERACT("Enter organisation name"),
    ORGANISATIONTYPEINTERACT("""
            Enter organisation type from list:
                COMMERCIAL,
                PUBLIC,
                PRIVATE_LIMITED_COMPANY,
                OPEN_JOINT_STOCK_COMPANY""");
    private String text;

    OrganizationText(String text) {
        this.text = text;
    }

}

