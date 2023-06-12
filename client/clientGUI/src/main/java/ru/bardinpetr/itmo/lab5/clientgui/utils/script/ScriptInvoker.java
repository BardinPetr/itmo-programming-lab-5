package ru.bardinpetr.itmo.lab5.clientgui.utils.script;

import ru.bardinpetr.itmo.lab5.client.controller.common.handlers.ClientCommandResponse;
import ru.bardinpetr.itmo.lab5.client.controller.common.handlers.UICallableCommand;
import ru.bardinpetr.itmo.lab5.client.ui.cli.IInvoker;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ScriptException;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.OrganizationModel;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.WorkerModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.bottom.CollectionInfoPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedAreaText;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedLabel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.impl.OrganizationTable;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.impl.WorkersTable;
import ru.bardinpetr.itmo.lab5.models.commands.api.*;
import ru.bardinpetr.itmo.lab5.models.commands.responses.UserPrintableAPICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScriptInvoker implements IInvoker {
    private final JPanel mainResultPanel;
    private final UIResources resources;

    public ScriptInvoker(JPanel resultPanel) {
        resources = UIResources.getInstance();
        this.mainResultPanel = resultPanel;
    }

    @Override
    public boolean invoke(UICallableCommand command, List<String> args) throws ScriptException {
        ClientCommandResponse<? extends UserPrintableAPICommandResponse> resp;
        try {
            resp = command.executeWithArgs(args);
        } catch (ScriptException ex) {
            throw ex;
        } catch (Exception ex) {
            resp = ClientCommandResponse.error(ex.getMessage());
        }
        if (resp == null) resp = ClientCommandResponse.ok();

        show(args.size() > 0 ? args.get(0) : null, resp);

        return resp.isSuccess();
    }

    private void show(String s, ClientCommandResponse<? extends UserPrintableAPICommandResponse> resp) {
        var resultPanel = new JPanel();
        resultPanel.setBorder(BorderFactory.createTitledBorder(s));

        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        if (!resp.isSuccess()) {
            var errorLabel = new JLabel(
                    resources.get("ScriptInvoker.scriptFailed.text") +
                            resources.get(
                                    (resp.message() != null
                                            ? resp.message()
                                            : "ScriptInvoker.scriptFailed.unknownError.text"
                                    )
                            )
            );
            errorLabel.setForeground(Color.RED);

            var errorPanel = new JPanel();
            errorPanel.add(errorLabel);
            resultPanel.add(errorPanel);
            return;
        }

        switch (s) {
            case "help" -> handleHelpResponse(resp.payload(), resultPanel);
            case "info" -> handleInfoResponse(resp.payload(), resultPanel);
            case "show" -> handleShowResponse(resp.payload(), resultPanel);
            case "add", "add_if_max", "add_if_min" -> handleAddResponse(resp.payload(), resultPanel);
//            case "update" -> handleUpdateResponse(resp.payload(), resultPanel);
//            case "remove_by_id" -> handleRemove_by_idResponse(resp.payload(), resultPanel);
//            case "clear" -> handleClearResponse(resp.payload(), resultPanel);
//            case "save" -> handleSaveResponse(resp.payload(), resultPanel);
//            case "execute_script" -> handleExecute_scriptResponse(resp.payload(), resultPanel);
            case "exit" -> handleExitResponse(resp.payload(), resultPanel);
            //            case "remove_greater" -> handleRemove_greaterResponse(resp.payload(), resultPanel);
            case "filter_less_than_position" -> handleFilter_less_than_positionResponse(resp.payload(), resultPanel);
            case "print_descending" -> handlePrint_descendingResponse(resp.payload(), resultPanel);
            case "print_unique_organization" -> handlePrint_unique_organizationResponse(resp.payload(), resultPanel);
            default -> defaultHandel(resp.payload(), resultPanel);
        }

        mainResultPanel.add(resultPanel);
    }

    private void defaultHandel(UserPrintableAPICommandResponse response, JPanel resultPanel) {
        resultPanel.add(new ResourcedLabel("ScriptInvoker.default.success"));
    }

    private void handleHelpResponse(UserPrintableAPICommandResponse response, JPanel resultPanel) {
        resultPanel.add(new ResourcedAreaText(
                        "ScriptInvoker.help.text"
                )
        );
    }

    private void handleInfoResponse(UserPrintableAPICommandResponse response, JPanel resultPanel) {
        var resp = ((InfoCommand.InfoCommandResponse) response).getResult();
        var panel = new CollectionInfoPanel();
        panel.setBDSize(resp.getItemsCount());
        panel.setInitDate(resp.getInitializationDate());
        resultPanel.add(
                panel
        );
    }

    private void handleShowResponse(UserPrintableAPICommandResponse response, JPanel resultPanel) {
        var resp = ((ShowCommand.ShowCommandResponse) response).getResult();
        resultPanel.add(
                createWorkerTable(resp)
        );
    }

    private void handleAddResponse(UserPrintableAPICommandResponse response, JPanel resultPanel) {
        var resp = ((AddCommand.AddCommandResponse) response);
        resultPanel.add(
                new ResourcedLabel("WorkerAddFrame.newIdMsg", ": " + resp.getId())
        );
    }

    private void handleExecute_scriptResponse(UserPrintableAPICommandResponse response, JPanel resultPanel) {
        var resp = ((ExecuteScriptCommand.ExecuteScriptCommandResponse) response);
        resultPanel.add(
                new JLabel(
                        resources.get(resp != null
                                ? resp.getUserMessage()
                                : "ScriptInvoker.scriptFailed.unknownError.text"
                        )
                ));
    }

    private void handleExitResponse(UserPrintableAPICommandResponse response, JPanel resultPanel) {
        System.exit(1);
    }

    //    private void handleAdd_if_maxResponse(UserPrintableAPICommandResponse response, JPanel resultPanel){
//        var resp = ((AddCommand.AddCommandResponse) response);
//}
//    private void handleAdd_if_minResponse(UserPrintableAPICommandResponse response, JPanel resultPanel){
//        var resp = ((AddCommand.AddCommandResponse) response);
//}
    private void handleFilter_less_than_positionResponse(UserPrintableAPICommandResponse response, JPanel resultPanel) {
        var resp = ((FilterLessPosCommand.FilterLessPosCommandResponse) response).getResult();
        resultPanel.add(
                createWorkerTable(resp)
        );
    }

    private void handlePrint_descendingResponse(UserPrintableAPICommandResponse response, JPanel resultPanel) {
        var resp = ((PrintDescendingCommand.PrintDescendingCommandResponse) response).getResult();
        resultPanel.add(
                createWorkerTable(resp)
        );
    }

    private void handlePrint_unique_organizationResponse(UserPrintableAPICommandResponse response, JPanel resultPanel) {
        var resp = ((UniqueOrganisationCommand.UniqueOrganisationCommandResponse) response).getOrganizations();
        resultPanel.add(
                createOrganizationTable(resp)
        );
    }

    private JPanel createWorkerTable(List<Worker> workers) {
        var tmpModel = new WorkerModel(false);
        tmpModel.addAll(workers);
        var table = new WorkersTable(tmpModel);
        table.setTableStatic(true);
        return table;
    }

    private JPanel createOrganizationTable(List<Organization> organizations) {
        var tmpModel = new OrganizationModel(false);
        tmpModel.addAll(organizations);
        var table = new OrganizationTable(tmpModel);
        table.setTableStatic(true);
        return table;
    }
}
