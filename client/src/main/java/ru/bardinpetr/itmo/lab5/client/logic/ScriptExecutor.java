package ru.bardinpetr.itmo.lab5.client.logic;

import ru.bardinpetr.itmo.lab5.client.tui.commands.controller.CommandScriptController;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.LocalExecuteScriptCommand;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ScriptExecutor extends Executor {
    private final CommandScriptController scriptController = new CommandScriptController();

    public ScriptExecutor() {
        registerOperation(
                LocalExecuteScriptCommand.class,
                i -> executeScript(i, new HashSet<>())
        );
    }

    private FileInputStream fileInputStream(LocalExecuteScriptCommand scriptCommand) {
        try {
            return new FileInputStream(scriptCommand.getFileName());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid script. File name: " + scriptCommand.getFileName());
        }
    }

    private LocalExecuteScriptCommand.LocalExecuteScriptCommandResponse executeScript(LocalExecuteScriptCommand scriptCommand, Set<String> paths) {
        List<Command> list = scriptController.run(fileInputStream(scriptCommand));
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
