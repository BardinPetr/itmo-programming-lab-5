package ru.bardinpetr.itmo.lab5.client.controller.common;

import java.util.Map;

public abstract class AbstractLocalCommand {
    public abstract CommandResponse execute(String cmdName, Map<String, Object> args);
}