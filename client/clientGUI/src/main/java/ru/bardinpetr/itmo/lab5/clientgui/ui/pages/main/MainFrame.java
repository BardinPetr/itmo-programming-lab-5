/*
 * Created by JFormDesigner on Tue Jun 06 21:04:37 MSK 2023
 */

package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.main;

import java.awt.event.*;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.bottom.component.BottomPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.bottom.component.BottomPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.userInfo.UsersInfo;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.userInfo.UsersInfoZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrame;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author zam12
 */
public class MainFrame extends ResourcedFrame {
    public MainFrame() {
        initComponents();
        setVisible(true);
    }



    private void workersMenuClicked(MouseEvent e) {
        new WorkerAddFrame();
    }

    private void workersMenuButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void orgsMenuClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void mapClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void scriptClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        mainPanel = new JPanel();
        bottomMenu = new BottomPanel();
        panel1 = new JPanel();
        menuBar = new JMenuBar();
        workersMenuButton = new JMenuItem();
        orgsMenuButton = new JMenuItem();
        mapMenuButton = new JMenuItem();
        scriptMenuButton = new JMenuItem();
        usersInfo = new UsersInfo();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== mainPanel ========
        {
            mainPanel.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
            . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing
            .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
            Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,mainPanel. getBorder () ) ); mainPanel. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
            public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName (
            ) ) )throw new RuntimeException( ) ;} } );
            mainPanel.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < mainPanel.getComponentCount(); i++) {
                    Rectangle bounds = mainPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = mainPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                mainPanel.setMinimumSize(preferredSize);
                mainPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(bottomMenu, BorderLayout.SOUTH);

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //======== menuBar ========
            {

                //---- workersMenuButton ----
                workersMenuButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        workersMenuButtonMouseClicked(e);
                        workersMenuClicked(e);
                    }
                });
                menuBar.add(workersMenuButton);

                //---- orgsMenuButton ----
                orgsMenuButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        orgsMenuClicked(e);
                    }
                });
                menuBar.add(orgsMenuButton);

                //---- mapMenuButton ----
                mapMenuButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mapClicked(e);
                    }
                });
                menuBar.add(mapMenuButton);

                //---- scriptMenuButton ----
                scriptMenuButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        scriptClicked(e);
                    }
                });
                menuBar.add(scriptMenuButton);
            }
            panel1.add(menuBar, BorderLayout.WEST);
            panel1.add(usersInfo, BorderLayout.EAST);
        }
        contentPane.add(panel1, BorderLayout.NORTH);

        initComponentsI18n();

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

    }

    protected void initComponentsI18n() {
        // JFormDesigner - Component i18n initialization - DO NOT MODIFY  //GEN-BEGIN:initI18n  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        ResourceBundle bundle = getResources();
        workersMenuButton.setText(bundle.getString("MainFrame.workersMenuButton.text"));
        orgsMenuButton.setText(bundle.getString("MainFrame.orgsMenuButton.text"));
        mapMenuButton.setText(bundle.getString("MainFrame.mapMenuButton.text"));
        scriptMenuButton.setText(bundle.getString("MainFrame.scriptMenuButton.text"));
        // JFormDesigner - End of component i18n initialization  //GEN-END:initI18n  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Artem
    private JPanel mainPanel;
    private BottomPanel bottomMenu;
    private JPanel panel1;
    private JMenuBar menuBar;
    private JMenuItem workersMenuButton;
    private JMenuItem orgsMenuButton;
    private JMenuItem mapMenuButton;
    private JMenuItem scriptMenuButton;
    private UsersInfo usersInfo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
