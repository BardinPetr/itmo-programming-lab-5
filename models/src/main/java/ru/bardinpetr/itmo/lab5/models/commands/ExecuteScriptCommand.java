package ru.bardinpetr.itmo.lab5.models.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    public static class ExecuteScriptCommandResponse extends ListCommandResponse<Response<ICommandResponse>> {
    }

    @Override
    public Field[] getInlineArgs(){
        return new Field[]{
                new Field("file_name", String.class)
        };
    }

}
