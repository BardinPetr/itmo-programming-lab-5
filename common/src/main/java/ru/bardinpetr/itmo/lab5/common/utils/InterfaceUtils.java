package ru.bardinpetr.itmo.lab5.common.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class InterfaceUtils {
    public static Set<String> getInterfaceMethodsNames(Class<?> cls) {
        return Arrays.stream(cls.getDeclaredMethods())
                .map(Method::getName)
                .collect(Collectors.toSet());
    }
}
