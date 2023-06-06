/*
 * Created by JFormDesigner on Tue Jun 06 20:33:23 MSK 2023
 */

package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.add;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author zam12
 */
public class OrgAddFrame extends ResourcedFrame {
    public OrgAddFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        orgInfoPanel = new JPanel();
        panel1 = new JPanel();
        addOrgButton = new JButton();
        orgrAddCancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== orgInfoPanel ========
        {
            orgInfoPanel.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing.
            border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax. swing .border . TitledBorder. CENTER
            ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069al\u006fg", java .awt . Font
            . BOLD ,12 ) ,java . awt. Color .red ) ,orgInfoPanel. getBorder () ) ); orgInfoPanel. addPropertyChangeListener(
            new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062or\u0064er"
            .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
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
            panel1.add(addOrgButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
            panel1.add(orgrAddCancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
        addOrgButton.setText(bundle.getString("OrgAddFrame.addOrgButton.text"));
        orgrAddCancelButton.setText(bundle.getString("OrgAddFrame.orgrAddCancelButton.text"));
        // JFormDesigner - End of component i18n initialization  //GEN-END:initI18n  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Artem
    private JPanel orgInfoPanel;
    private JPanel panel1;
    private JButton addOrgButton;
    private JButton orgrAddCancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
