package ru.bardinpetr.itmo.lab5.models.serdes.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class SerDesException extends Exception {
    private Type direction;
    private JsonProcessingException internalException;

    public enum Type {
        SERIALIZE, DESERIALIZE
    }
}
