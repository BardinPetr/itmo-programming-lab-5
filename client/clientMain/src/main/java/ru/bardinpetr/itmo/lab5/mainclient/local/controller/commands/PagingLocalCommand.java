package ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.client.api.commands.APICommandRegistry;
import ru.bardinpetr.itmo.lab5.client.controller.common.APIUILocalCommand;
import ru.bardinpetr.itmo.lab5.client.controller.common.handlers.ClientCommandResponse;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.InteractSpecialSymbols;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.requests.PagingAPICommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.UserAPICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.UserPrintableAPICommandResponse;

import java.util.List;

/**
 * Worker update command
 */
public abstract class PagingLocalCommand extends APIUILocalCommand {
    private static final int count = 10;

    public PagingLocalCommand(APIClientReceiver api, UIReceiver ui, APICommandRegistry registry) {
        super(api, ui, registry);
    }

    @Override
    public String getExternalName() {
        return "null";
    }

    @Override
    protected UserAPICommand retrieveAPICommand(String name) {
        return null;
    }

    protected abstract PagingAPICommand createPagedCommand(int offset, int count);


    @Override
    public ClientCommandResponse<? extends UserPrintableAPICommandResponse> executeWithArgs(List<String> args) {
        int offset = -count;
        InteractSpecialSymbols input = InteractSpecialSymbols.UP;
        boolean isOut = false;
        int prevOffset = 0;
        while (true) {

            APICommandResponse resp;
            prevOffset = offset;
            try {

                if (input.equals(InteractSpecialSymbols.UP)) {
                    offset += count;
                } else if (input.equals(InteractSpecialSymbols.DOWN)) {

                    if (offset <= 0) {
                        uiReceiver.display("Encountered start of response");
                        input = uiReceiver.interactSpecial();
                        offset = -count;
                        continue;
                    }
                    offset -= count;
                } else if (input.equals(InteractSpecialSymbols.EXIT)) {
                    break;
                } else {
                    uiReceiver.display("Wrong choice");
                }


                resp = apiClientReceiver.call(createPagedCommand(offset, count));

                if (resp.isSuccess()) {
                    var showRes = (PagingAPICommand.DefaultCollectionCommandResponse) resp;
                    uiReceiver.display(showRes.getUserMessage());
                    isOut = false;
                } else {
                    if (isOut) offset -= count;
                    isOut = true;
                    uiReceiver.display(resp.getTextualResponse());
                }
            } catch (APIClientException e) {
                offset = prevOffset;
                uiReceiver.display("Server error. Try later.");
            }

            uiReceiver.display("↑- предыдущий ↓- следующий end- закончить команду");
            input = uiReceiver.interactSpecial();
        }
        return new ClientCommandResponse<>(
                true,
                "Finished success",
                null
        );
    }


}
