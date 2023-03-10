package ru.bardinpetr.itmo.lab5.client.tui.commands.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.parser.CommandParser;
import ru.bardinpetr.itmo.lab5.client.parser.CommandRegister;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.tui.ICommandIOCallback;
import ru.bardinpetr.itmo.lab5.client.tui.View;
import ru.bardinpetr.itmo.lab5.common.serdes.ObjectMapperFactory;
import ru.bardinpetr.itmo.lab5.models.commands.LocalExecuteScriptCommand;
import ru.bardinpetr.itmo.lab5.models.commands.ServerExecuteScriptCommand.ExecuteScriptCommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.Response;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for console UI
 */
public class CLIController {

    protected Scanner scanner;

    protected CommandParser cmdParser;
    protected View viewer;
    protected ICommandIOCallback callback;
    protected ObjectMapper mapper = ObjectMapperFactory.createMapper();

    public CLIController(View viewer, InputStream inputStream, ICommandIOCallback callback) {
        scanner = new Scanner(inputStream);

        this.callback = callback;


        CommandRegister cmdRegister = new CommandRegister();

        this.viewer = viewer;

        cmdParser = cmdRegister.getParser(scanner, viewer, () -> {
            System.exit(0);
        });
    }

    /**
     * method for script output
     *
     * @param resp2 script response
     */
    private void runScript(Response<ICommandResponse> resp2) {
        if (resp2.isSuccess()) {
            ExecuteScriptCommandResponse payload2 = (ExecuteScriptCommandResponse) resp2.getPayload();
            payload2.getResult().forEach(
                    i -> {
                        if (i.getPayload() != null) {
                            viewer.show(i.getPayload().getUserMessage());
                        }
                    }); // print script command responses
        } else {
            viewer.show("Error: " + resp2.getText());
        }

    }

    /**
     * method for starting program execution
     */
    public void run() {
        viewer.show(RussianText.get(TextKeys.GREEETING));
        viewer.suggestInput();
        while (scanner.hasNextLine()) {
            try {
                Command cmd = cmdParser.parse();
                var resp = callback.callback(cmd);
                if (resp.isSuccess()) {
                    ICommandResponse payload = resp.getPayload();
                    if (payload != null) {
                        if (payload.getClass() == LocalExecuteScriptCommand.LocalExecuteScriptCommandResponse.class) {
                            var resp2 = callback.callback((Command) payload);
                            runScript(resp2);
                        } else {
                            viewer.show(payload.getUserMessage()); // print general command response
                        }
                    } else {
                        viewer.show("OK!");
                    }
                } else {
                    viewer.show("Error: " + resp.getText());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid argument. Stop command interaction");
            } catch (ParserException e) {
                System.err.println(e.getMessage());
            }
            viewer.suggestInput();
        }
    }
}
