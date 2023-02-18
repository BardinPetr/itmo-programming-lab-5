package ru.bardinpetr.itmo.lab5.common.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ValueDeserializer {
    private final ObjectMapper mapper = new ObjectMapper();

    public <K> K deserialize(Class<K> kClass, String string) throws IllegalArgumentException {
        return mapper.convertValue(string, kClass);
    }
}
