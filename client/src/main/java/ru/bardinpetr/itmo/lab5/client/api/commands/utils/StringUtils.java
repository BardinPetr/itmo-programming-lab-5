package ru.bardinpetr.itmo.lab5.client.api.commands.utils;

public class StringUtils {
    public static String capitalize(String inputString) {
        char firstLetter = inputString.charAt(0);
        char capitalFirstLetter = Character.toUpperCase(firstLetter);
        return capitalFirstLetter + inputString.substring(1);
    }
}