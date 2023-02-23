package ru.bardinpetr.itmo.lab5.client.tui.commands.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.client.parser.CommandParser;
import ru.bardinpetr.itmo.lab5.client.parser.CommandRegister;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.client.tui.ICommandIOComeback;
import ru.bardinpetr.itmo.lab5.client.tui.View;
import ru.bardinpetr.itmo.lab5.common.serdes.ObjectMapperFactory;
import ru.bardinpetr.itmo.lab5.models.commands.LocalExecuteScriptCommand;
import ru.bardinpetr.itmo.lab5.models.commands.ServerExecuteScriptCommand.ExecuteScriptCommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;

import java.io.InputStream;
import java.util.Scanner;

public class CLIController {

    protected Scanner scanner;

    protected CommandParser cmdParser;
    protected View viewer;
    protected ICommandIOComeback callback;
    protected ObjectMapper mapper = ObjectMapperFactory.createMapper();

    public CLIController(View viewer, InputStream inputStream, ICommandIOComeback callback) {
        scanner = new Scanner(inputStream);

        this.callback = callback;



        CommandRegister cmdRegister = new CommandRegister();

        this.viewer = viewer;

        cmdParser = cmdRegister.getParser(mapper, scanner, viewer);
    }

    public void run() {
        viewer.show(RussianText.get(TextKeys.GREEETING));
        while (scanner.hasNextLine()) {
            try {
                Command cmd = cmdParser.parse();
                var resp = callback.callback(cmd);
                if (resp.isSuccess()) {
                    ICommandResponse payload = resp.getPayload();
                    if (payload != null) {
                        if (payload.getClass() == LocalExecuteScriptCommand.LocalExecuteScriptCommandResponse.class) {
                            var resp2 = callback.callback((Command) payload);
                            if (resp2.isSuccess()) {
                                ExecuteScriptCommandResponse payload2 = (ExecuteScriptCommandResponse) resp2.getPayload();
                                payload2.getResult().forEach(
                                        i -> viewer.show(i.getText()) // print script command responses
                                );
                            } else {
                                viewer.show("Error: " + resp2.getText());
                            }
                        } else {
                            viewer.show(payload.getUserMessage()); // print general command response
                        }
                    } else {
                        viewer.show("OK!");
                    }
                } else {
                    if (cmd.getClass() == LocalExecuteScriptCommand.class) viewer.show("Invalid script");
                    else viewer.show("Error: " + resp.getText());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid argument. Stop command interaction");
            } catch (ParserException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
