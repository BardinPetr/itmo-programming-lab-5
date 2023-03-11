package ru.bardinpetr.itmo.lab5.models.data.collection;

import lombok.Data;
import lombok.NonNull;

import java.time.ZonedDateTime;

@Data
public class CollectionInfo {
    @NonNull
    private String name;
    @NonNull
    private String type;
    @NonNull
    private ZonedDateTime initializationDate;
    @NonNull
    private Integer itemsCount;
}
