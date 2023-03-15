package ru.bardinpetr.itmo.lab5.client.api;

import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.base.responses.Response;

public interface APIClientReceiver {
    Response<ICommandResponse> call(Command cmd);
}
