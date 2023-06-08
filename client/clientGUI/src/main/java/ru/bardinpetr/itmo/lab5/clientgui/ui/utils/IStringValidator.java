package ru.bardinpetr.itmo.lab5.clientgui.ui.utils;

import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;
@FunctionalInterface
public interface IStringValidator {
    public ValidationResponse validate(String s);
}
