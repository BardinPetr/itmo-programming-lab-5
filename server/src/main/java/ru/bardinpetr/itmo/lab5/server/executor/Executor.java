package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.models.commands.Command;
import ru.bardinpetr.itmo.lab5.models.commands.resonses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.resonses.Response;

import java.util.HashMap;
import java.util.Map;


/**
 * Class for mapping client Commands to local implementations of these commands.
 * Register handlers with registerOperation
 */
public class Executor {

    private final Map<Class<? extends Command>, Operation<Command, ICommandResponse>> operationMap = new HashMap<>();

    /**
     * Register function "{@code operation}" for replying on "{@code cmdClass}" calls
     *
     * @param cmdClass  class of input command object
     * @param operation function taking Command and returning Response on this command
     * @param <T>       type of command
     */
    public <T extends Command> void registerOperation(Class<T> cmdClass, Operation<T, ICommandResponse> operation) {
        @SuppressWarnings("unchecked") var baseOperation = (Operation<Command, ICommandResponse>) operation;
        operationMap.put(cmdClass, baseOperation);
    }

    /**
     * Call handler for specified command object.
     * If no handler registered returns error in Response.
     *
     * @param cmd Command from client
     * @return Response for this command or Response(success=false) if no handler exist
     */
    public <T extends ICommandResponse> Response<T> execute(Command cmd) {
        var op = operationMap.get(cmd.getClass());
        if (op == null) return Response.error("Command not implemented");
        try {
            @SuppressWarnings("unchecked") var res = (T) op.apply(cmd);
            return Response.success(res);
        } catch (Exception ex) {
            return Response.error(ex);
        }
    }
}
