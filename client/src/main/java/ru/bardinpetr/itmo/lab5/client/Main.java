package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.parser.CommandRegister;
import ru.bardinpetr.itmo.lab5.client.parser.ParserException;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;



public class Main {
    public static void main(String[] args) {


        var cmdRegister = new CommandRegister();
        var cmdParser = cmdRegister.regist();
        Executor executor = new Executor();

        try {
            Command cmd = cmdParser.parse("update 2");
            var resp = executor.execute(cmd);
            if (resp.isSuccess()) {

            } else {

            }


            System.out.println(cmd);
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }


    }

}
