/*
 * Created by JFormDesigner on Tue Jun 06 19:44:56 MSK 2023
 */

package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.update;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * @author zam12
 */
public class WorkerUpdateFrame extends ResourcedFrame {
    public WorkerUpdateFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        workerInfoPanel = new JPanel();
        panel1 = new JPanel();
        updateWorkerButton = new JButton();
        workerUpdateCancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== workerInfoPanel ========
        {
            workerInfoPanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
            javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax
            .swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java
            .awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt
            .Color.red),workerInfoPanel. getBorder()));workerInfoPanel. addPropertyChangeListener(new java.beans.
            PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".
            equals(e.getPropertyName()))throw new RuntimeException();}});
            workerInfoPanel.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < workerInfoPanel.getComponentCount(); i++) {
                    Rectangle bounds = workerInfoPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = workerInfoPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                workerInfoPanel.setMinimumSize(preferredSize);
                workerInfoPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(workerInfoPanel, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
            panel1.add(updateWorkerButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 5), 0, 0));
            panel1.add(workerUpdateCancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(panel1, BorderLayout.SOUTH);

        initComponentsI18n();

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    protected void initComponentsI18n() {
        // JFormDesigner - Component i18n initialization - DO NOT MODIFY  //GEN-BEGIN:initI18n  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        ResourceBundle bundle = getResources();
        updateWorkerButton.setText(bundle.getString("WorkerUpdateFrame.updateWorkerButton.text"));
        workerUpdateCancelButton.setText(bundle.getString("WorkerUpdateFrame.workerUpdateCancelButton.text"));
        // JFormDesigner - End of component i18n initialization  //GEN-END:initI18n  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Artem
    private JPanel workerInfoPanel;
    private JPanel panel1;
    private JButton updateWorkerButton;
    private JButton workerUpdateCancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
