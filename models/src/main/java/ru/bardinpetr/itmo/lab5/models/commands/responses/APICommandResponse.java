package ru.bardinpetr.itmo.lab5.models.commands.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("userMessage")
public interface APICommandResponse {
    default String getUserMessage() {
        return toString();
    }
}
