package ru.bardinpetr.itmo.lab5.client.logic;

import ru.bardinpetr.itmo.lab5.client.tui.commands.controller.CommandScriptController;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.LocalExecuteScriptCommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.APICommand;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Executer for scripts command which can track looping in scripts
 */
public class ScriptExecutor extends Executor {
    private final CommandScriptController scriptController = new CommandScriptController();

    /**
     * registration commands
     */
    public ScriptExecutor() {
        registerOperation(
                LocalExecuteScriptCommand.class,
                i -> executeScript(i, new HashSet<>())
        );
    }

    /**
     * creating an input stream
     *
     * @param scriptCommand command that stores the path to the file
     * @return input stream
     */
    private FileInputStream fileInputStream(LocalExecuteScriptCommand scriptCommand) {
        try {
            return new FileInputStream(scriptCommand.getFileName());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid script. File name: " + scriptCommand.getFileName());
        }
    }

    /**
     * the main method for executing scripts.
     * The method executes the commands one by one and collects them into a sheet.
     * If another script is executed during operation, it jumps into it recursively,
     * then adds all the commands to the initial list.
     *
     * @param scriptCommand command to find the desired script file
     * @param paths         paths to files that have already been encountered earlier in the recursion
     * @return response with list of commands
     */

    private LocalExecuteScriptCommand.LocalExecuteScriptCommandResponse executeScript(LocalExecuteScriptCommand scriptCommand, Set<String> paths) {
        List<APICommand> list = scriptController.run(fileInputStream(scriptCommand));
        list = list.stream().flatMap(i -> {
            if (i.getClass() == LocalExecuteScriptCommand.class) {
                var path = ((LocalExecuteScriptCommand) i).getFileName();
                if (paths.add(path)) {
                    var res = executeScript((LocalExecuteScriptCommand) i, paths);
                    paths.remove(path);
                    return Stream.of(res);
                } else {
                    throw new RuntimeException("!");
                }
            }
            return Stream.of(i);
        }).toList();
        return new LocalExecuteScriptCommand.LocalExecuteScriptCommandResponse(list);
    }

}
