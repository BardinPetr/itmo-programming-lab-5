package ru.bardinpetr.itmo.lab5.client.controller.comands;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.controller.comands.common.APILocalCommand;
import ru.bardinpetr.itmo.lab5.client.tui.UIReceiver;


public class GeneralAPILocalCommand extends APILocalCommand {

    public GeneralAPILocalCommand(APIClientReceiver api, UIReceiver ui) {
        super(api, ui);
    }
}
