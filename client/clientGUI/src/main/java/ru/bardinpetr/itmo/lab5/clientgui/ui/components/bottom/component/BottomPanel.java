/*
 * Created by JFormDesigner on Tue Jun 06 21:29:00 MSK 2023
 */

package ru.bardinpetr.itmo.lab5.clientgui.ui.components.bottom.component;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.lang.*;

/**
 * @author zam12
 */
public class BottomPanel extends ResourcedPanel {
    public BottomPanel() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        infoText = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        bdDateField = new JLabel();
        label4 = new JLabel();
        workersCountField = new JLabel();
        langLayout = new LanguageChanger();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
        ( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border
        . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
        . Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
        propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
        ; }} );
        setLayout(new GridLayout());
        add(infoText);
        add(label1);
        add(label2);
        add(bdDateField);
        add(label4);
        add(workersCountField);
        add(langLayout);

        initComponentsI18n();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setVisible(true);
    }

    protected void initComponentsI18n() {
        // JFormDesigner - Component i18n initialization - DO NOT MODIFY  //GEN-BEGIN:initI18n  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        ResourceBundle bundle = getResources();
        infoText.setText(bundle.getString("bottomPanel.infoText.text"));
        label1.setText(bundle.getString("bottomPanel.label1.text"));
        label2.setText(bundle.getString("bottomPanel.label2.text"));
        label4.setText(bundle.getString("bottomPanel.label4.text"));
        // JFormDesigner - End of component i18n initialization  //GEN-END:initI18n  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Artem
    private JLabel infoText;
    private JLabel label1;
    private JLabel label2;
    private JLabel bdDateField;
    private JLabel label4;
    private JLabel workersCountField;
    private LanguageChanger langLayout;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
