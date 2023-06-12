package ru.bardinpetr.itmo.lab5.clientgui.ui.components.script;

import ru.bardinpetr.itmo.lab5.client.ui.cli.ScriptExecutor;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ScriptException;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ScriptRecursionRootException;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.utils.script.ScriptCommandRegistry;
import ru.bardinpetr.itmo.lab5.clientgui.utils.script.ScriptInvoker;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.mainclient.api.commands.UserAPICommandRegistry;
import ru.bardinpetr.itmo.lab5.mainclient.api.commands.UserAPICommandsDescriptionHolder;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

import static javax.swing.SwingUtilities.invokeLater;

public class ScriptPanel extends ResourcedPanel {
    private final ScriptExecutor scriptExecutor;
    private JFileChooser scriptChooser;

    private JPanel resultPanel;
    private JButton executeScriptButton;

    public ScriptPanel() {
        initComponents();
        setVisible(true);

        var descriptionHolder = new UserAPICommandsDescriptionHolder();
        scriptExecutor = new ScriptExecutor(
                descriptionHolder,
                new ScriptInvoker(resultPanel)
        );

        var apiRegistry = new UserAPICommandRegistry();
        new ScriptCommandRegistry(
                scriptExecutor,
                apiRegistry,
                null //will be replaced in script executor
        );

    }

    private JScrollPane getResultArea(){
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        var t = new JScrollPane(
                resultPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        t.getVerticalScrollBar().setUnitIncrement(16);
        return t;
    }

    private void initComponents(){
        executeScriptButton = new JButton();
        scriptChooser = new JFileChooser();

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(getResultArea());

        var buttonsPanel = new Panel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(executeScriptButton);
        add(buttonsPanel);

        scriptChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".zb");
            }

            @Override
            public String getDescription() {
                return getResources().get("MainFrame.scriptChooseText.description");
            }
        });

        executeScriptButton.addActionListener((e) ->{
            scriptChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            scriptChooser.showDialog(this, getResources().get("MainFrame.scriptChooseText.text"));
        });

        scriptChooser.addActionListener((e)->{
            if (scriptChooser.getSelectedFile() == null) return;
            else {
                var scriptPath = scriptChooser.getSelectedFile().getPath();
                invokeLater(() -> executeScript(scriptPath));
            }
        });

        initComponentsI18n();
    }

    private void executeScript(String path){
        resultPanel.removeAll();
        try {
            scriptExecutor.process(path);
        } catch (FileAccessException | ScriptException | ScriptRecursionRootException e) {
            Object[] options = {
                    UIResources.getInstance().get("optionalAnswers.Ok")
            };
            JOptionPane.showOptionDialog(
                    this,
                    getResources().get("ScriptLocalCommand.invalidScript.text"),
                    getResources().get(e.getMessage()),
                    JOptionPane.OK_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,     //do not use a custom Icon
                    options,
                    options[0]
            );
            return;
        }
    }


    @Override
    protected void initComponentsI18n() {
        executeScriptButton.setText(UIResources.getInstance().get("ScriptPanel.executeScriptButton.text"));
    }
}
