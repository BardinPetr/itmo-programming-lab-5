package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

import java.util.List;

public class LocalExecuteScriptCommand extends Command {
    @NonNull
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    @Override
    public String getType() {
        return "execute_script";
    }

    @Override
    public Field[] getInlineArgs() {
        return new Field[]{
                new Field("fileName", String.class)
        };
    }

    @Override
    public LocalExecuteScriptCommandResponse createResponse() {
        return new LocalExecuteScriptCommandResponse(List.of());
    }

    @Data
    public static class LocalExecuteScriptCommandResponse extends ServerExecuteScriptCommand implements ICommandResponse {
        public LocalExecuteScriptCommandResponse(@NonNull List<Command> commands) {
            super(commands);
        }
        @Override
        public String getUserMessage() {
            return null;
        }
    }
}
