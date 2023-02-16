package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.bardinpetr.itmo.lab5.models.commands.validation.IValidator;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

@Getter
@ToString
@EqualsAndHashCode
public class FiledWithDesc extends Field{
    private String promtMsg;
    private IValidator validator;
    public <T> FiledWithDesc(String name, Class<T> kClass, String requestMsg, IValidator<T> validator){
        super(name, kClass);
        this.promtMsg = requestMsg;
        this.validator = validator;
    }

    public <T> FiledWithDesc(String name, Class<T> kclass, String requestMsg){
        super(name, kclass);
        this.promtMsg = requestMsg;
        this.validator = (IValidator<T>) s -> new ValidationResponse(true, "");
    }


}