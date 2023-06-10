/*
 * Created by JFormDesigner on Tue Jun 06 16:02:56 MSK 2023
 */

package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.login;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * @author petr
 */
public class login extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Bardin Petr
    private JPanel vSpacer1;
    private JPanel hSpacer1;
    private JLabel label1;
    private JPanel hSpacer2;
    private JPanel vSpacer2;
    private JLabel label2;
    private JTextField textField1;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JCheckBox checkBox1;
    private JPanel panel1;
    private JButton button2;
    private JButton button3;
    private JPanel vSpacer3;
    public login() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Bardin Petr
        ResourceBundle bundle = ResourceBundle.getBundle("ru.bardinpetr.itmo.lab5.clientgui.i18n.data.guitexts");
        vSpacer1 = new JPanel(null);
        hSpacer1 = new JPanel(null);
        label1 = new JLabel();
        hSpacer2 = new JPanel(null);
        vSpacer2 = new JPanel(null);
        label2 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        checkBox1 = new JCheckBox();
        panel1 = new JPanel();
        button2 = new JButton();
        button3 = new JButton();
        vSpacer3 = new JPanel(null);

        //======== this ========
        setTitle(bundle.getString("loginPage.this.title"));
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 305, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 1.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};
        contentPane.add(vSpacer1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(hSpacer1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label1 ----
        label1.setText(bundle.getString("loginPage.label1.text"));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(hSpacer2, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));
        contentPane.add(vSpacer2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText(bundle.getString("loginPage.label2.text"));
        contentPane.add(label2, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(textField1, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText(bundle.getString("loginPage.label3.text"));
        contentPane.add(label3, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(passwordField1, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- checkBox1 ----
        checkBox1.setText(bundle.getString("loginPage.checkBox1.text"));
        contentPane.add(checkBox1, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

            //---- button2 ----
            button2.setText(bundle.getString("loginPage.button2.text"));
            panel1.add(button2);

            //---- button3 ----
            button3.setText(bundle.getString("loginPage.button3.text"));
            panel1.add(button3);
        }
        contentPane.add(panel1, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(vSpacer3, new GridBagConstraints(0, 8, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
