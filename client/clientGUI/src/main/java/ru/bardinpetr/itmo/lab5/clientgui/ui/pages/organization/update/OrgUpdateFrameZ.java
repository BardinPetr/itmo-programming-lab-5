package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.update;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.info.OrganizationInfoPanelZ;
import ru.bardinpetr.itmo.lab5.models.data.Organization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

public class OrgUpdateFrameZ extends ResourcedFrame {
    private OrganizationInfoPanelZ orgInfoPanel;
    private JPanel panel1;
    private JButton updateOrgButton;
    private JButton orgUpdateCancelButton;
    private ResourceBundle bundle = getResources();
    private Organization defaultOrganization;

    public OrgUpdateFrameZ(Organization defaultOrganization) {
        this.defaultOrganization = defaultOrganization;
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        orgInfoPanel = new OrganizationInfoPanelZ(defaultOrganization);
        panel1 = new JPanel();
        updateOrgButton = new JButton();
        orgUpdateCancelButton = new JButton();

        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        panel1.setLayout(new BorderLayout());
        panel1.add(updateOrgButton, BorderLayout.WEST);
        panel1.add(orgUpdateCancelButton, BorderLayout.EAST);

        add(orgInfoPanel, BorderLayout.NORTH);
        add(panel1, BorderLayout.SOUTH);


        orgUpdateCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        updateOrgButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var worker = orgInfoPanel.getOrganization();
                if (!worker.isAllowed) {
                    JOptionPane.showMessageDialog(orgInfoPanel, bundle.getString(worker.msg), bundle.getString("AddFrame.input.error.text"), JOptionPane.ERROR_MESSAGE);
                }
                else {
                    //TODO add organization command
                    System.out.println(worker);
                }
            }
        });



        initComponentsI18n();

        pack();
        setLocationRelativeTo(getOwner());
    }
    @Override
    protected void initComponentsI18n() {
        bundle = getResources();
        updateOrgButton.setText(bundle.getString("OrgAddFrame.addOrgButton.text"));
        orgUpdateCancelButton.setText(bundle.getString("OrgAddFrame.orgrAddCancelButton.text"));
    }
}
