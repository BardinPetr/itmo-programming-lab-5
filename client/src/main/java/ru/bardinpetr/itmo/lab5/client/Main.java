package ru.bardinpetr.itmo.lab5.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import ru.bardinpetr.itmo.lab5.client.tui.ObjectScanner;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;


public class Main {
    public static void main(String[] args) {
        var mapper = new ObjectMapper();
        String timeFormat = "dd-MM-yyyy";
        mapper.setDateFormat(new SimpleDateFormat(timeFormat));
        var formatter = new DateTimeFormatterBuilder().appendPattern(timeFormat)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter(); //TODO reformat
        var timeModule =
                new JavaTimeModule()
                        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter))
                        .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));

        mapper.registerModule(timeModule);
        Worker coor = ObjectScanner.scan(Worker.class);

        System.out.println(coor);

//        var cmdRegister = new CommandRegister();
//        var cmdParser = cmdRegister.regist();
//
//        try {
//            Command cmd = cmdParser.parse("update 2");
//            System.out.println(cmd);
//        } catch (ParserException e) {
//            System.out.println(e.getMessage());
//        }

    }

}
