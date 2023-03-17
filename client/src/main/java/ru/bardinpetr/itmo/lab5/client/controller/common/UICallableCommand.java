package ru.bardinpetr.itmo.lab5.client.controller.common;

import java.util.List;

public interface UICallableCommand {
    void executeWithArgs(List<String> args);
}
