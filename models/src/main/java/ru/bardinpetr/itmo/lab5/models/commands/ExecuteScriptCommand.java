package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.resonses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.resonses.ListCommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.resonses.Response;

import java.util.List;

@Data
@NoArgsConstructor
public class ExecuteScriptCommand extends Command {
    @NonNull
    public List<Command> commands;

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
