package ru.bardinpetr.itmo.lab5.models.data.collection;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class CollectionInfo {
    @NonNull
    private String name;
    @NonNull
    private String type;
    @NonNull
    private Date initializationDate;
    @NonNull
    private Integer itemsCount;
}