package ru.bardinpetr.itmo.lab5.client.ui.cli;

import ru.bardinpetr.itmo.lab5.client.controller.common.UICallableCommand;
import ru.bardinpetr.itmo.lab5.client.controller.registry.CommandRegistry;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ScriptRecursionController;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ScriptException;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;

import java.util.List;

/**
 * Class for recursive parsing of nested script files
 */
public class ScriptExecutor {
    private final ScriptRecursionController recursionController;
    private CommandRegistry commandRegistry = null;

    public ScriptExecutor() {
        this.recursionController = new ScriptRecursionController();
    }

    /**
     * Set CommandRegistry to use
     *
     * @param commandRegistry CommandRegistry object from which commands should be executed
     */
    public void setRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    /**
     * Open and execute all commands in file with specified path
     *
     * @param path file path
     * @throws FileAccessException if script could not be read
     * @throws ScriptException     if recursion occurs
     */
    public void process(String path) throws FileAccessException, ScriptException {
        if (commandRegistry == null) throw new RuntimeException("No command registry");

        var isNormal = recursionController.enter(path);
        if (!isNormal) {
            recursionController.clear();
            throw new ScriptException("Recursion detected at %s".formatted(path));
        }

        FileIOController fileIOController = new FileIOController(path);

        UIReceiver uiReceiver = new CLIController(
                ConsolePrinter.getStub(),
                fileIOController.openReadStream()
        );

        var currentRegistry = commandRegistry.withUI(uiReceiver);
        while (uiReceiver.hasNextLine()) {
            var line = uiReceiver.nextLine();
            if (line == null) break;

            var userArgs = line.split("\\s+");
            var command = (UICallableCommand) currentRegistry.getCommand(userArgs[0]);
            if (command == null) {
                uiReceiver.display("Command not found");
                continue;
            }

            try {
                command.executeWithArgs(List.of(userArgs));
            } catch (ScriptException ex) {
                recursionController.clear();
                throw ex;
            }
        }

        recursionController.leave(path);
    }
}
