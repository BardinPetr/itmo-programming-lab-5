package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.add;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.info.OrganizationInfoPanelZ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

public class OrgAddFrameZ extends ResourcedFrame {
    private OrganizationInfoPanelZ orgInfoPanel;
    private JPanel panel1;
    private JButton addOrgButton;
    private JButton orgAddCancelButton;
    private ResourceBundle bundle = getResources();

    public OrgAddFrameZ() {
        initComponents();
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
        addOrgButton.setText(bundle.getString("OrgAddFrame.addOrgButton.text"));
        orgAddCancelButton.setText(bundle.getString("OrgAddFrame.orgrAddCancelButton.text"));
    }
}
