package ru.bardinpetr.itmo.lab5.clientgui.ui.utils;

import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

public interface ISuccessCommandHandler {
    void handle(APICommandResponse response);
}
