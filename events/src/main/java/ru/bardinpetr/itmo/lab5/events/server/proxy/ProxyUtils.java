package ru.bardinpetr.itmo.lab5.events.server.proxy;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.InvocationTargetException;

public class ProxyUtils {

    @SuppressWarnings("unchecked")
    public static <T> T create(MethodHandler handler, Class<T> targetClass, Object... args) {
        var factory = new ProxyFactory();
        factory.setSuperclass(targetClass);

        var argTypes = new Class<?>[args.length];
        for (int i = 0; i < argTypes.length; i++) argTypes[i] = args[i].getClass();

        try {
            return (T) factory.create(argTypes, args, handler);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
