package ru.bardinpetr.itmo.lab5.client.tui.commands.controller;

import ru.bardinpetr.itmo.lab5.client.controller.comands.common.AbstractLocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.comands.common.IRRegistryCommand;
import ru.bardinpetr.itmo.lab5.client.controller.comands.common.exception.NoSuchCommandException;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.tui.Printer;
import ru.bardinpetr.itmo.lab5.client.tui.newThings.CLIUtilityController;
import ru.bardinpetr.itmo.lab5.client.tui.newThings.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.tui.newThings.ConsoleReader;
import ru.bardinpetr.itmo.lab5.client.tui.newThings.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.ServerExecuteScriptCommand.ExecuteScriptCommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.Response;

import java.io.InputStream;

/**
 * Class for console UI
 */
public class CLIController{
    UIReceiver uiReceiver;
    ConsolePrinter printer = new ConsolePrinter();
    ConsoleReader reader = new ConsoleReader();
    private IRRegistryCommand registryCommand;
    public CLIController(Printer viewer, InputStream inputStream) {
        uiReceiver = new CLIUtilityController();
    }
    /**
     * method for script output
     *
     * @param resp2 script response
     */
    @Deprecated
//    private void runScript(Response<ICommandResponse> resp2) {
//        if (resp2.isSuccess()) {
//            ExecuteScriptCommandResponse payload2 = (ExecuteScriptCommandResponse) resp2.getPayload();
//            payload2.getResult().forEach(
//                    i -> {
//                        if (i.getPayload() != null) {
//                            viewer.show(i.getPayload().getUserMessage());
//                        }
//                    }); // print script command responses
//        } else {
//            viewer.show("Error: " + resp2.getText());
//        }
//
//    }

    /**
     * method for starting program execution
     */
    public void run() {
        printer.display(RussianText.get(TextKeys.GREEETING));
        printer.suggestInput();
        while (reader.hasNextLine()) {
            String[] userArgs = reader.readLine().split(" ");
            String commandName = userArgs[0];

            AbstractLocalCommand command = null;
            try {
                command = registryCommand.getByName(commandName);
                command.execute(commandName, userArgs); //TODO delete first arg

            } catch (NoSuchCommandException e) {

            }

        }
    }
}
