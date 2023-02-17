package ru.bardinpetr.itmo.lab5.models.fields;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.bardinpetr.itmo.lab5.models.commands.validation.IValidator;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

@Getter
@ToString
@EqualsAndHashCode
//TODO generics
public class FieldWithDesc extends Field {
    private final String promptMsg;
    private final IValidator validator;

    public <T> FieldWithDesc(String name, Class<T> kClass, String requestMsg, IValidator<T> validator) {
        super(name, kClass);
        this.promptMsg = requestMsg;
        this.validator = validator;
    }

    public <T> FieldWithDesc(String name, Class<T> kclass, String requestMsg) {
        super(name, kclass);
        this.promptMsg = requestMsg;
        this.validator = (IValidator<T>) s -> new ValidationResponse(true, "");
    }


}