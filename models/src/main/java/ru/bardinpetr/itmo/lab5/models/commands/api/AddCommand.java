package ru.bardinpetr.itmo.lab5.models.commands.api;

import lombok.*;
import ru.bardinpetr.itmo.lab5.models.commands.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerValidator;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

/**
 * Class of add command
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AddCommand extends APICommand {
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
        return (new WorkerValidator()).validateAll(element);
    }

    @Override
    public AddCommandResponse createResponse() {
        return new AddCommandResponse();
    }

    @Getter
    @Setter
    public static class AddCommandResponse implements APICommandResponse {
        private Integer id;

        @Override
        public String getUserMessage() {
            return "Success";
        }
    }
}