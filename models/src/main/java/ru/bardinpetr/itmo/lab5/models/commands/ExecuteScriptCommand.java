package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ListCommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.Response;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ExecuteScriptCommand extends APICommand {
    @NonNull
    private List<APICommand> commands;

    @Override
    public String getType() {
        return "server_execute_script";
    }

    @Override
    public Field[] getInlineArgs() {
        return new Field[]{
                new Field("fileName", String.class)
        };
    }

    @Override
    public ExecuteScriptCommandResponse createResponse() {
        return new ExecuteScriptCommandResponse();
    }

    public static class ExecuteScriptCommandResponse extends ListCommandResponse<Response<ICommandResponse>> {
        @Override
        public String getUserMessage() {
            String respond = "";
            for (var i : getResult()) {
                                respond += i.getPayload().getUserMessage() + "\n";
                        }
                        return respond;
                }
        }
}