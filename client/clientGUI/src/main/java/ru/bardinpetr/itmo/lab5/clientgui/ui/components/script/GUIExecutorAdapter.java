package ru.bardinpetr.itmo.lab5.clientgui.ui.components.script;

import ru.bardinpetr.itmo.lab5.client.ui.cli.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ScriptException;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ScriptRecursionRootException;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.clientgui.utils.script.ScriptCommandRegistry;
import ru.bardinpetr.itmo.lab5.clientgui.utils.script.ScriptInvoker;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.mainclient.api.commands.UserAPICommandRegistry;
import ru.bardinpetr.itmo.lab5.mainclient.api.commands.UserAPICommandsDescriptionHolder;

import javax.swing.*;
import java.util.function.Consumer;

public class GUIExecutorAdapter extends SwingWorker<DataContainer<JPanel>, Integer> {
    private final ScriptExecutor scriptExecutor;
    private final JPanel mainResultPanel;
    private final Consumer<Boolean> handle;
    private JPanel tmpPanel;
    private String scriptPath;
    public GUIExecutorAdapter(String scriptPath, JPanel mainResultPanel, Consumer<Boolean> handle) {
        this.scriptPath = scriptPath;
        this.mainResultPanel = mainResultPanel;
        this.handle = handle;
        this.tmpPanel = new JPanel();
        tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));

        var descriptionHolder = new UserAPICommandsDescriptionHolder();
        scriptExecutor = new ScriptExecutor(
                descriptionHolder,
                new ScriptInvoker(tmpPanel)
        );

        var apiRegistry = new UserAPICommandRegistry();
        new ScriptCommandRegistry(
                scriptExecutor,
                apiRegistry,
                null
        );

    }

    private UIResources getResources(){
        return UIResources.getInstance();
    }
    @Override
    protected DataContainer<JPanel> doInBackground() throws Exception {
        try {
            scriptExecutor.process(scriptPath);
        } catch (FileAccessException | ScriptException | ScriptRecursionRootException e) {
            Object[] options = {
                    UIResources.getInstance().get("optionalAnswers.Ok")
            };
            JOptionPane.showOptionDialog(
                    null,
                    getResources().get(e.getMessage()),
                    getResources().get("ScriptLocalCommand.invalidScript.text"),
                    JOptionPane.OK_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,     //do not use a custom Icon
                    options,
                    options[0]
            );
            return new DataContainer<>(
                    false,
                    tmpPanel,
                    "");
        }
        mainResultPanel.add(tmpPanel);
        return new DataContainer<>(
                true,
                tmpPanel,
                "");
    }
    @Override
    protected void done() {
        try {
            var res = get();
            mainResultPanel.removeAll();
            mainResultPanel.add(res.data);
            handle.accept(res.isAllowed);
        } catch (Exception ignore) {}
    }
}
