package ru.bardinpetr.itmo.lab5.client.logic;

import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.ExitCommand;
import ru.bardinpetr.itmo.lab5.models.commands.HelpCommand;

public class UIExecutor extends Executor {
    public UIExecutor() {
        registerVoidOperation(
                ExitCommand.class,
                req -> System.exit(0)
        );
        registerVoidOperation(
                HelpCommand.class,
                req -> System.out.println(RussianText.getMap().get(TextKeys.HELP))
        );
    }

}
