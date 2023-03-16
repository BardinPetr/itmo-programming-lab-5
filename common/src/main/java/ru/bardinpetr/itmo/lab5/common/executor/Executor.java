package ru.bardinpetr.itmo.lab5.common.executor;

import ru.bardinpetr.itmo.lab5.common.executor.operations.NoReturnOperation;
import ru.bardinpetr.itmo.lab5.common.executor.operations.Operation;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class for mapping client Commands to local implementations of these commands.
 * Register handlers with registerOperation
 */
public class Executor {
    public static int MAX_RECURSION_DEPTH = 10;

    private final Map<Class<? extends APICommand>, Operation<APICommand, ICommandResponse>> operationMap = new HashMap<>();
    private final List<Executor> childExecutors = new ArrayList<>();

    /**
     * Register function "{@code operation}" for replying on "{@code cmdClass}" calls.
     *
     * @param cmdClass  class of input command object
     * @param operation function taking input Command and returning Response object
     * @param <T>       type of command
     */
    public <T extends APICommand> void registerOperation(Class<T> cmdClass, Operation<T, ICommandResponse> operation) {
        @SuppressWarnings("unchecked") var baseOperation = (Operation<APICommand, ICommandResponse>) operation;
        operationMap.put(cmdClass, baseOperation);
    }

    /**
     * Register function "{@code operation}" for replying on "{@code cmdClass}" calls.
     * Used for commands that do not have specific response type
     *
     * @param cmdClass  class of input command object
     * @param operation function taking input Command
     * @param <T>       type of command
     */
    public <T extends APICommand> void registerVoidOperation(Class<T> cmdClass, NoReturnOperation<T> operation) {
        var baseOperation = (Operation<APICommand, ICommandResponse>) (cmd) -> {
            @SuppressWarnings("unchecked") var command = (T) cmd;
            operation.apply(command);
            return cmd.createResponse();
        };
        operationMap.put(cmdClass, baseOperation);
    }

    /**
     * Join other executor with this so locally not resolved commands will be forwarded to this executor
     *
     * @param child Executor to pass commands to
     */
    public void registerExecutor(Executor child) {
        childExecutors.add(child);
    }

    /**
     * Call handler for specified command object.
     * If no handler registered returns error in Response.
     *
     * @param cmd Command from client
     * @return Response for this command or Response(success=false) if no handler exist
     */
    public Response<ICommandResponse> execute(APICommand cmd) {
        return execute(cmd, MAX_RECURSION_DEPTH);
    }

    protected Response<ICommandResponse> execute(APICommand cmd, int recursionDepth) {
        var op = operationMap.get(cmd.getClass());
        if (op == null) {
            if (recursionDepth > 0) {
                for (Executor e : childExecutors) {
                    var res = e.execute(cmd, recursionDepth - 1);
                    if (res.isResolved()) return res;
                }
            }
            return Response.noResolve();
        }
        try {
            var validation = cmd.validate();
            if (!validation.isAllowed())
                return Response.error("Validation failed: %s".formatted(validation.getMsg()));
            return Response.success(op.apply(cmd));
        } catch (Exception ex) {
            return Response.error(ex.getMessage());
        }
    }

    /**
     * Execute script (sequence of commands) one by one.
     * Errors won't stop execution.
     *
     * @param cmds commands
     * @return list of response payloads for each command in input order
     */
    public List<Response<ICommandResponse>> executeBatch(List<APICommand> cmds) {
        var result = new ArrayList<Response<ICommandResponse>>();
        boolean anyFailed = false;
        for (var cmd : cmds) {
            if (anyFailed) {
                result.add(Response.error("%s skipped".formatted(cmd.getType())));
                continue;
            }
            var resp = execute(cmd);
            if (!resp.isSuccess()) anyFailed = true;
            result.add(resp);
        }
        return result;
    }
}
