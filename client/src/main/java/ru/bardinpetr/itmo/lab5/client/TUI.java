package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.parser.CommandRegister;
import ru.bardinpetr.itmo.lab5.client.parser.ParserException;
import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.resonses.ICommandResponse;

import java.util.Map;
import java.util.Scanner;

public class TUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        View view = new ConsolePrinter();
        Map<TextKeys, String> texts = RussianText.getMap();
        CommandRegister cmdRegister = new CommandRegister();
        var cmdParser = cmdRegister.regist();

        Executor executor = new Executor();


        try {
            Command cmd = cmdParser.parse("info");
            var resp = executor.execute(cmd);
            if (resp.isSuccess()) {
                ICommandResponse payload = resp.getPayload();
                view.show(payload.getUserMessage());
            } else {
                view.show("Error!" + resp.getText());
            }


            //System.out.println(cmd);
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }


    }
}
