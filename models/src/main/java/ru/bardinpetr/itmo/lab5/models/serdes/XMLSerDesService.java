package ru.bardinpetr.itmo.lab5.models.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides functions for serializing and deserializing POJO to XML based on jackson.dataformat.xml
 *
 * @param <T> Base type to operate on
 */
public class XMLSerDesService<T> extends SerDesService<T> {

    public XMLSerDesService(Class<? extends T> baseClass) {
        super(baseClass);
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        var mapper = new XmlMapper();

        var formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        var timeModule =
                new SimpleModule()
                        .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
                        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));

        mapper.registerModule(timeModule);
        return mapper;
    }
}
