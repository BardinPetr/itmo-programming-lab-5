/*
 * Created by JFormDesigner on Tue Jun 06 20:40:09 MSK 2023
 */

package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.update;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author zam12
 */
public class OrgUpdateFrame extends ResourcedFrame {
    public OrgUpdateFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        orgInfoPanel = new JPanel();
        panel1 = new JPanel();
        updateOrgButton = new JButton();
        orgUpdateCancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== orgInfoPanel ========
        {
            orgInfoPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
            awt .Font .BOLD ,12 ), java. awt. Color. red) ,orgInfoPanel. getBorder( )) )
            ; orgInfoPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;
            orgInfoPanel.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < orgInfoPanel.getComponentCount(); i++) {
                    Rectangle bounds = orgInfoPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = orgInfoPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                orgInfoPanel.setMinimumSize(preferredSize);
                orgInfoPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(orgInfoPanel, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
            panel1.add(updateOrgButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 5), 0, 0));
            panel1.add(orgUpdateCancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
        updateOrgButton.setText(bundle.getString("OrgUpdateFrame.updateOrgButton.text"));
        orgUpdateCancelButton.setText(bundle.getString("OrgUpdateFrame.orgUpdateCancelButton.text"));
        // JFormDesigner - End of component i18n initialization  //GEN-END:initI18n  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Artem
    private JPanel orgInfoPanel;
    private JPanel panel1;
    private JButton updateOrgButton;
    private JButton orgUpdateCancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
