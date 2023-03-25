package ru.bardinpetr.itmo.lab5.client.api.commands.utils;

import java.lang.invoke.MethodType;

public class ClassUtils {
    public static <T> Class<T> wrap(Class<T> c) {
        return (Class<T>) MethodType.methodType(c).wrap().returnType();
    }
}
