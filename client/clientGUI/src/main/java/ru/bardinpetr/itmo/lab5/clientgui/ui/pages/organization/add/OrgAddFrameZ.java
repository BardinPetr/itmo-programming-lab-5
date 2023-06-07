package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.add;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;

public class OrgAddFrameZ extends ResourcedFrame {
    private JPanel orgInfoPanel;
    private JPanel panel1;
    private JButton addOrgButton;
    private JButton orgrAddCancelButton;

    public OrgAddFrameZ() {
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        orgInfoPanel = new JPanel();
        panel1 = new JPanel();
        addOrgButton = new JButton();
        orgrAddCancelButton = new JButton();

        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        panel1.setLayout(new BorderLayout());
        panel1.add(addOrgButton, BorderLayout.WEST);
        panel1.add(orgrAddCancelButton, BorderLayout.EAST);

        add(orgInfoPanel, BorderLayout.NORTH);
        add(panel1, BorderLayout.SOUTH);


        orgrAddCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        initComponentsI18n();

        pack();
        setLocationRelativeTo(getOwner());
    }
    @Override
    protected void initComponentsI18n() {
        ResourceBundle bundle = getResources();
        addOrgButton.setText(bundle.getString("OrgAddFrame.addOrgButton.text"));
        orgrAddCancelButton.setText(bundle.getString("OrgAddFrame.orgrAddCancelButton.text"));
    }
}
