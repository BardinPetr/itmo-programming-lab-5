package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.update;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.info.OrganizationHeaderPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.info.OrganizationInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.commands.api.UpdateOrgCommand;
import ru.bardinpetr.itmo.lab5.models.data.Organization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrgUpdateFrameZ extends ResourcedFrame {
    private final Organization defaultOrganization;
    private OrganizationInfoPanelZ orgInfoPanel;
    private JButton updateOrgButton;
    private JButton orgUpdateCancelButton;
    private UIResources resources = getResources();
    private OrganizationHeaderPanel header;

    public OrgUpdateFrameZ(Organization defaultOrganization) {
        this.defaultOrganization = defaultOrganization;
        initComponents();
        setVisible(true);
    }

    protected void initComponents() {
        header = new OrganizationHeaderPanel(defaultOrganization);
        orgInfoPanel = new OrganizationInfoPanelZ(defaultOrganization);

        updateOrgButton = new JButton();
        orgUpdateCancelButton = new JButton();

        setLayout(new GridBagLayout());

        var headerConstrains = GridConstrains.placedAdd(0,0);
        headerConstrains.gridwidth = GridBagConstraints.REMAINDER;
        add(header, headerConstrains);
        var infoConstrains = GridConstrains.placedAdd(0,1);
        infoConstrains.gridwidth = GridBagConstraints.REMAINDER;
        add(orgInfoPanel, infoConstrains);
        add(updateOrgButton, GridConstrains.placedAdd(0, 2));
        add(orgUpdateCancelButton, GridConstrains.placedAdd(1, 2));


        orgUpdateCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });

        updateOrgButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                var organization = orgInfoPanel.getOrganization();
                if (!organization.isAllowed) {
                    JOptionPane.showMessageDialog(orgInfoPanel, UIResources.getInstance().get(organization.msg), UIResources.getInstance().get("AddFrame.input.error.text"), JOptionPane.ERROR_MESSAGE);
                } else {
                    var ogs = orgInfoPanel.getOrganization().data;
                    var cmd = new UpdateOrgCommand(ogs.getId(), ogs);
                    new APICommandMenger().sendCommand(
                            cmd,
                            null,
                            "OrgUpdateFrameZ.updateError.text",
                            (e2) -> {}
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
        setTitle(resources.get("OrgUpdateFrameZ.title"));
        updateOrgButton.setText(resources.get("OrgUpdateFrameZ.addOrgButton.text"));
        orgUpdateCancelButton.setText(resources.get("OrgAddFrame.orgrAddCancelButton.text"));
    }
}
