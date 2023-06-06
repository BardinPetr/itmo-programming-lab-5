/*
 * Created by JFormDesigner on Tue Jun 06 19:31:46 MSK 2023
 */

package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.*;

/**
 * @author zam12
 */
public class WorkerAddFrame extends ResourcedFrame {
    public WorkerAddFrame() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        workerInfoPanel = new WorkerInfoPanelZ();
        panel1 = new JPanel();
        nornalAdd = new JRadioButton();
        addWorkerButton = new JButton();
        ifMaxAdd = new JRadioButton();
        ifMainAdd = new JRadioButton();
        workerAddCancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(workerInfoPanel, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
            swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border
            . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog"
            ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder
            ( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
            .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException
            ( ); }} );
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
            panel1.add(nornalAdd, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
            panel1.add(addWorkerButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(ifMaxAdd, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
            panel1.add(ifMainAdd, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
            panel1.add(workerAddCancelButton, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
        }
        contentPane.add(panel1, BorderLayout.SOUTH);

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(nornalAdd);
        buttonGroup1.add(ifMaxAdd);
        buttonGroup1.add(ifMainAdd);

        initComponentsI18n();

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    protected void initComponentsI18n() {
        // JFormDesigner - Component i18n initialization - DO NOT MODIFY  //GEN-BEGIN:initI18n  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        ResourceBundle bundle = getResources();
        nornalAdd.setText(bundle.getString("WorkerAddFrame.nornalAdd.text"));
        addWorkerButton.setText(bundle.getString("WorkerAddFrame.addWorkerButton.text"));
        ifMaxAdd.setText(bundle.getString("WorkerAddFrame.ifMaxAdd.text"));
        ifMainAdd.setText(bundle.getString("WorkerAddFrame.ifMainAdd.text"));
        workerAddCancelButton.setText(bundle.getString("WorkerAddFrame.workerAddCancelButton.text"));
        // JFormDesigner - End of component i18n initialization  //GEN-END:initI18n  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Artem
    private WorkerInfoPanelZ workerInfoPanel;
    private JPanel panel1;
    private JRadioButton nornalAdd;
    private JButton addWorkerButton;
    private JRadioButton ifMaxAdd;
    private JRadioButton ifMainAdd;
    private JButton workerAddCancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
