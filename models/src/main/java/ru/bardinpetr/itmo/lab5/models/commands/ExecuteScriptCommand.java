package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ListCommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.Response;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ExecuteScriptCommand extends Command {
    @NonNull
    private List<Command> commands;

    @Override
    public String getType() {
        return "execute_script";
    }

    @Override
    public ExecuteScriptCommandResponse createResponse() {
        return new ExecuteScriptCommandResponse();
    }

    @Override
    public Field[] getInlineArgs() {
        return new Field[]{
                new Field("file_name", String.class)
        };
    }

    public static class ExecuteScriptCommandResponse extends ListCommandResponse<Response<ICommandResponse>> {
    }
}
