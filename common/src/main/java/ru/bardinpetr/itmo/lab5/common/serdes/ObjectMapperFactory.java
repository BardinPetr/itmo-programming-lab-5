package ru.bardinpetr.itmo.lab5.common.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;

public class ObjectMapperFactory {

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();

        String timeFormat = "dd-MM-yyyy";
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        format.setLenient(false);
        mapper.setDateFormat(format);

        var localDateFormatter = new DateTimeFormatterBuilder().appendPattern(timeFormat)
                .toFormatter();

        var timeModule =
                new JavaTimeModule()
                        .addDeserializer(LocalDate.class, new LocalDateDeserializer(localDateFormatter))
                        .addSerializer(LocalDate.class, new LocalDateSerializer(localDateFormatter));
        mapper.registerModule(timeModule);
//        mapper.registerModule(new JavaTimeModule());
        //mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return mapper;
    }
}
