/*
 * Created by JFormDesigner on Tue Jun 06 20:03:35 MSK 2023
 */

package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author zam12
 */
public class WorkerShowPanel extends ResourcedPanel {
    public WorkerShowPanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        workerTablePanel = new JPanel();
        panel1 = new JPanel();
        openAddWorkerPlane = new JButton();
        clearWorkerButton = new JButton();
        removeGreaterButton = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );
        setLayout(new GridLayout(2, 1));

        //======== workerTablePanel ========
        {
            workerTablePanel.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < workerTablePanel.getComponentCount(); i++) {
                    Rectangle bounds = workerTablePanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = workerTablePanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                workerTablePanel.setMinimumSize(preferredSize);
                workerTablePanel.setPreferredSize(preferredSize);
            }
        }
        add(workerTablePanel);

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};
            panel1.add(openAddWorkerPlane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
            panel1.add(clearWorkerButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
            panel1.add(removeGreaterButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel1);

        initComponentsI18n();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    protected void initComponentsI18n() {
        // JFormDesigner - Component i18n initialization - DO NOT MODIFY  //GEN-BEGIN:initI18n  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        ResourceBundle bundle = getResources();
        openAddWorkerPlane.setText(bundle.getString("WorkerShowPanel.openAddWorkerPlane.text"));
        clearWorkerButton.setText(bundle.getString("WorkerShowPanel.clearWorkerButton.text"));
        removeGreaterButton.setText(bundle.getString("WorkerShowPanel.removeGreaterButton.text"));
        // JFormDesigner - End of component i18n initialization  //GEN-END:initI18n  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Artem
    private JPanel workerTablePanel;
    private JPanel panel1;
    private JButton openAddWorkerPlane;
    private JButton clearWorkerButton;
    private JButton removeGreaterButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
