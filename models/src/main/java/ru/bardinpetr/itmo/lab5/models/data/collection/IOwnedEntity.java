package ru.bardinpetr.itmo.lab5.models.data.collection;

public interface IOwnedEntity {
    String getOwner();

    boolean setOwner(String owner);
}
