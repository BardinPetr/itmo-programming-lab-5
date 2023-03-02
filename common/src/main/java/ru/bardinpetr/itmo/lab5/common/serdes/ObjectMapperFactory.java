package ru.bardinpetr.itmo.lab5.common.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class ObjectMapperFactory {
    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();

        String timeFormat = "dd-MM-yyyy";
        mapper.setDateFormat(new SimpleDateFormat(timeFormat));

        var localDateTimeFormatter = new DateTimeFormatterBuilder().appendPattern(timeFormat)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();

        var timeModule =
                new JavaTimeModule()
                        .addDeserializer(LocalDate.class, new LocalDateDeserializer(localDateTimeFormatter))
                        .addSerializer(LocalDate.class, new LocalDateSerializer(localDateTimeFormatter));
        mapper.registerModule(timeModule);

        return mapper;
    }
}
