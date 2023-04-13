package ru.bardinpetr.itmo.lab5.client.controller.common;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.api.commands.APICommandRegistry;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;


/**
 * Command that can be used to encapsulate any APICommand in one LocalCommand
 */
public class GeneralAPIUILocalCommand extends APIUILocalCommand {

    public GeneralAPIUILocalCommand(APIClientReceiver api, UIReceiver ui, APICommandRegistry registry) {
        super(api, ui, registry);
    }

    @Override
    public String getExternalName() {
        return null;
    }
}
