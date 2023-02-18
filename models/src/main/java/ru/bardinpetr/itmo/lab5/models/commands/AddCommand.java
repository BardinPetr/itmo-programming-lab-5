package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.*;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerValidation;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

/**
 * Class of add command
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AddCommand extends Command {
    @NonNull
    public Worker element;

    @Override
    public String getType() {
        return "add";
    }

    @Override
    public Field[] getInteractArgs() {
        return new Field[]{
                new Field("element", Worker.class)
        };
    }

    @Override
    public ValidationResponse validate() {
        return WorkerValidation.validateAll(element);
    }

    @Override
    public AddCommandResponse createResponse() {
        return new AddCommandResponse();
    }

    @Getter
    @Setter
    public static class AddCommandResponse implements ICommandResponse {
        private Long id;

        @Override
        public String getUserMessage() {
            return null;
        }
    }
}
