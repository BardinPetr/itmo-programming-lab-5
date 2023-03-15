package ru.bardinpetr.itmo.lab5.client.controller.comands.common;

public abstract class AbstractLocalCommand {

    public abstract void execute(String commandName, Object[] inlineArgs);
}
