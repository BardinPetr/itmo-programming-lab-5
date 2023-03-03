package ru.bardinpetr.itmo.lab5.models.fields;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.bardinpetr.itmo.lab5.models.commands.validation.IValidator;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

@Getter
@ToString
@EqualsAndHashCode
public class FieldWithDesc<T> extends Field {
    private final String promptMsg;
    private final IValidator<T> validator;
    private final boolean nullAble;

    public FieldWithDesc(String name, Class<T> kClass, String requestMsg, IValidator<T> validator, boolean nullAble) {
        super(name, kClass);
        this.promptMsg = requestMsg;
        this.validator = validator;
        this.nullAble = nullAble;
    }

    public FieldWithDesc(String name, Class<T> kclass, String requestMsg, boolean nullAble) {
        super(name, kclass);
        this.promptMsg = requestMsg;
        this.validator = s -> new ValidationResponse(true, "");
        this.nullAble = nullAble;

    }
}