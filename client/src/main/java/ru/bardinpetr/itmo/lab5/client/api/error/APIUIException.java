package ru.bardinpetr.itmo.lab5.client.api.error;

import ru.bardinpetr.itmo.lab5.client.ui.texts.Texts;

public class APIUIException extends RuntimeException {
    public APIUIException(Throwable cause) {
        super("%s: %s".formatted(Texts.get(Texts.TextKeys.APIEXCEPTION), cause.getMessage()));
    }
}
