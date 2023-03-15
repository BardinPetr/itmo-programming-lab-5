package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.api.connectors.LocalExecutorAPIConnector;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.tui.commands.controller.CLIController;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.server.MainExecutor;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println(RussianText.get(TextKeys.INVALID_APP_ARGUMENTS));
            System.exit(1);
            return;
        }

        FileIOController file;
        try {
            file = new FileIOController(args[0]);
        } catch (FileAccessException e) {
            System.err.printf(RussianText.get(TextKeys.FILEIO_ERROR_MESSAGE_TEMLPATE), e.getMessage());
            System.exit(1);
            return;
        }

        var serverExecutor = new MainExecutor(file);
        var api = new LocalExecutorAPIConnector(serverExecutor);

    }
}

//class UI {
//    void main() {
//        whule(){
//            cmd_name, cmd_args = get;
//            var cmd = getCmdByName(cmd_name);
//            cmd.execute(cmd_args)
//        }
//    }
//
//    <T> T fill(Class<T> target) {
//        fill(target, null);
//    }
//    <T> T fill(Class<T> target, T def) {
//
//    }
//}
//
//
//class ScriptCommand extends ApiCommand {
//
//}
//
//class ApiCommand extends AbstractClientCommand{
//
//    vodi execute() {
//        var cmd = uiRecv.fill(getServerCommandByName(cmd), );
//        var resp = srvRecv.excure(cmd);
//        ui.errror() ? ui.ok()
//    }
//}
//
//class AbstractClientCommand {
//
//}