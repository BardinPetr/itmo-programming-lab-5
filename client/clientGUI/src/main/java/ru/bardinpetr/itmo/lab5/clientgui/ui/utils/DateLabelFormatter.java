package ru.bardinpetr.itmo.lab5.clientgui.ui.utils;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "dd MMMM yyyy";
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern, Locale.US);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parse(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;

            return dateFormatter.format(
                    LocalDateTime.ofInstant(
                            cal.toInstant(),
                            ZoneId.systemDefault()
                    ).toLocalDate());
        }

        return "";
    }

}
