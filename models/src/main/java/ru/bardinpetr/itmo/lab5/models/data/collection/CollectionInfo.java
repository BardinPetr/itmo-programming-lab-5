package ru.bardinpetr.itmo.lab5.models.data.collection;

import lombok.Data;

import java.util.Date;

@Data
public class CollectionInfo {
    private String name;
    private String type;
    private Date initializationDate;
    private Long itemsCount;
}
