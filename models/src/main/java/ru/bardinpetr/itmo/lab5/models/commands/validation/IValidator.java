package ru.bardinpetr.itmo.lab5.models.commands.validation;

import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

@FunctionalInterface
public interface IValidator<T> {
    ValidationResponse validate(T s);

}
