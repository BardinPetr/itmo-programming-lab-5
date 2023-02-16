package ru.bardinpetr.itmo.lab5.models.resonses;


import lombok.Data;

@Data
public class Response {
    private boolean success;
    private String error;
}
