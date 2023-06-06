/*
 * Created by JFormDesigner on Tue Jun 06 20:14:01 MSK 2023
 */

package ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.show;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * @author zam12
 */
public class OrganizationShowPanel extends JPanel {
    public OrganizationShowPanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        ResourceBundle bundle = ResourceBundle.getBundle("ru.bardinpetr.itmo.lab5.clientgui.i18n.data.guitexts");
        organizationTablePanel = new JPanel();
        panel1 = new JPanel();
        openAddOrgPlane = new JButton();
        clearOrgButton = new JButton();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
        ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
        .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt
        . Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
        propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
        ;} } );
        setLayout(new GridLayout(2, 0));

        //======== organizationTablePanel ========
        {
            organizationTablePanel.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < organizationTablePanel.getComponentCount(); i++) {
                    Rectangle bounds = organizationTablePanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = organizationTablePanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                organizationTablePanel.setMinimumSize(preferredSize);
                organizationTablePanel.setPreferredSize(preferredSize);
            }
        }
        add(organizationTablePanel);

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

            //---- openAddOrgPlane ----
            openAddOrgPlane.setText(bundle.getString("OrganizationShowPanel.openAddOrgPlane.text"));
            panel1.add(openAddOrgPlane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- clearOrgButton ----
            clearOrgButton.setText(bundle.getString("OrganizationShowPanel.clearOrgButton.text"));
            panel1.add(clearOrgButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(panel1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Artem
    private JPanel organizationTablePanel;
    private JPanel panel1;
    private JButton openAddOrgPlane;
    private JButton clearOrgButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
