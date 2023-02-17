package ru.bardinpetr.itmo.lab5.client.tui;

import ru.bardinpetr.itmo.lab5.client.parser.CommandParser;
import ru.bardinpetr.itmo.lab5.client.parser.CommandRegister;
import ru.bardinpetr.itmo.lab5.client.parser.error.ParserException;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class CommandIOController {
    private Scanner scanner;
    private Map<TextKeys, String> texts;
    private CommandParser cmdParser;
    private View viewer;
    private ICommandIOComeback callback;

    public CommandIOController(View viewer, InputStream inputStream, ICommandIOComeback callback) {
        scanner = new Scanner(inputStream);
        this.callback = callback;
        texts = RussianText.getMap();
        CommandRegister cmdRegister = new CommandRegister();
        cmdParser = cmdRegister.regist();
        this.viewer = viewer;
    }

    public void run() {
        viewer.show(texts.get(TextKeys.GREEETING));
        while (true) {
            try {
                Command cmd = cmdParser.parse(scanner.nextLine());
                var resp = callback.callback(cmd);
                if (resp.isSuccess()) {
                    ICommandResponse payload = resp.getPayload();
                    if (resp.getPayload() != null)
                        viewer.show(payload.getUserMessage());
                    else
                        viewer.show("OK!");
                } else {
                    viewer.show("Error: " + resp.getText());
                }
                //System.out.println(cmd);
            } catch (ParserException e) {
                System.out.println("1" + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid argument. Stop command interaction");
            }
        }


    }
}
