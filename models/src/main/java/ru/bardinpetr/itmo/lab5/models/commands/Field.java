package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;

@Data
public class Field {
    private String name;
    private Class<?> valueClass;
    public Field(){}
    public Field(String name, Class<?> kClass){
        this.name = name;
        this.valueClass = kClass;
    }
}
