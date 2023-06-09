package ru.bardinpetr.itmo.lab5.clientgui.utils;

import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Stream;

public class ListUtil {
    public static <T> Stream<T> stream(Enumeration<T> in) {
        return Collections.list(in).stream();
    }
}
