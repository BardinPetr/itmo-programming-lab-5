package ru.bardinpetr.itmo.lab5.models.commands.validation;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ValueDeserializer {
    private final ObjectMapper mapper = new ObjectMapper();
    public <K> K Deserialize(Class<K> kClass, String string) throws IllegalArgumentException{
        return mapper.convertValue(string, kClass);
    }
}
