package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.add;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.info.OrganizationInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddOrgCommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import static javax.swing.SwingUtilities.invokeLater;

public class OrgAddFrameZ extends ResourcedFrame {
    private OrganizationInfoPanelZ orgInfoPanel;
    private JPanel panel1;
    private JButton addOrgButton;
    private JButton orgAddCancelButton;
    private UIResources resources = getResources();

    public OrgAddFrameZ() {
        initComponents();
        setResizable(false);
        setVisible(true);
    }

    protected void initComponents(){
        orgInfoPanel = new OrganizationInfoPanelZ(null);
        panel1 = new JPanel();
        addOrgButton = new JButton();
        orgAddCancelButton = new JButton();

        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        panel1.setLayout(new BorderLayout());
        panel1.add(addOrgButton, BorderLayout.WEST);
        panel1.add(orgAddCancelButton, BorderLayout.EAST);

        add(orgInfoPanel, BorderLayout.NORTH);
        add(panel1, BorderLayout.SOUTH);


        orgAddCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });

        addOrgButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                var organization = orgInfoPanel.getOrganization();
                if (!organization.isAllowed) {
                    JOptionPane.showMessageDialog(
                            orgInfoPanel,
                            resources.get(organization.msg),
                            resources.get("AddFrame.input.error.text"),
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                else {
                    var command = new AddOrgCommand();
                    command.element = organization.data;
                    new APICommandMenger().sendCommand(
                            command,
                            orgInfoPanel,
                            "OrgAddFrameZ.addOrgError.text",
                            (response) ->{
                                var resp = (AddOrgCommand.AddOrgCommandResponse) response;
                                JOptionPane.showConfirmDialog(
                                        orgInfoPanel,
                                        getResources().get("OrgAddFrameZ.newIdMsg")+
                                                ": " + resp.getId(),
                                        getResources().get("OrgAddFrameZ.newIdMsg")
                                        , JOptionPane.PLAIN_MESSAGE
                                );
                            }

                    );
                }
            }
        });

        initComponentsI18n();

        pack();
        setLocationRelativeTo(getOwner());
    }
    @Override
    protected void initComponentsI18n() {
        resources = getResources();
        setTitle(resources.get("OrgAddFrameZ.title"));
        addOrgButton.setText(resources.get("OrgAddFrame.addOrgButton.text"));
        orgAddCancelButton.setText(resources.get("OrgAddFrame.orgrAddCancelButton.text"));
    }
}
