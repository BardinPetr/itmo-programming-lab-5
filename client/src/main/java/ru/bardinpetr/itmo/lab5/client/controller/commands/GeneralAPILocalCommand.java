package ru.bardinpetr.itmo.lab5.client.controller.commands;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.controller.common.APILocalCommand;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;


/**
 * Command that can be used to encapsulate any APICommand in one LocalCommand
 */
public class GeneralAPILocalCommand extends APILocalCommand {

    public GeneralAPILocalCommand(APIClientReceiver api, UIReceiver ui) {
        super(api, ui);
    }

    @Override
    public String getExternalName() {
        return null;
    }
}
