package ru.bardinpetr.itmo.lab5.clientgui.i18n.data;

import java.util.ListResourceBundle;

public class GUITexts extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
            {"bottomPanel.infoText.text", "collection type"},
            {"bottomPanel.label1.text", "workers"},
            {"bottomPanel.label2.text", "initialization date"},
            {"bottomPanel.label4.text", "count"},
            {"loginform.label5.text", "text"},
            {"loginpage.checkBox1.text", "text"},
            {"loginpage.button2.text", "text"},
            {"loginpage.button3.text", "text"},
            {"loginpage.this.title", "Login"},
            {"loginpage.label1.text", "text"},
            {"loginpage.label2.text", "text"},
            {"loginpage.label3.text", "text"},
            {"MainFrame.scriptMenuButton.text", "scripts"},
            {"MainFrame.workersMenuButton.text", "workers"},
            {"MainFrame.mapMenuButton.text", "map"},
            {"MainFrame.orgsMenuButton.text", "organizations"},
            {"OrgAddFrame.addOrgButton.text", "add"},
            {"OrgAddFrame.orgrAddCancelButton.text", "cancel"},
            {"OrganizationShowPanel.clearOrgButton.text", "Clear"},
            {"OrganizationShowPanel.openAddOrgPlane.text", "Add"},
            {"OrgUpdateFrame.updateOrgButton.text", "update"},
            {"OrgUpdateFrame.orgUpdateCancelButton.text", "cancel"},
            {"userInfo.workersCountText.text", "owned workers count:"},
            {"userInfo.workersCountField.text", "-2"},
            {"userInfo.username.text", "username"},
            {"userInfo.usernameField.text", "name"},
            {"WorkerAddFrame.addWorkerButton.text", "add"},
            {"WorkerAddFrame.workerAddCancelButton.text", "cancel"},
            {"WorkerAddFrame.nornalAdd.text", "Normal"},
            {"WorkerAddFrame.ifMaxAdd.text", "If max"},
            {"WorkerAddFrame.ifMainAdd.text", "if min"},
            {"WorkerInfoPanel.label1.text", "name"},
            {"WorkerInfoPanel.label2.text", "salary"},
            {"WorkerInfoPanel.label3.text", "start date"},
            {"WorkerInfoPanel.label4.text", "end date"},
            {"WorkerInfoPanel.label5.text", "coordinates x"},
            {"WorkerInfoPanel.label6.text", "coordinates y"},
            {"WorkerInfoPanel.label7.text", "organization id"},
            {"WorkerInfoPanel.label8.text", "position"},
            {"WorkerShowPanel.openAddWorkerPlane.text", "Add"},
            {"WorkerShowPanel.clearWorkerButton.text", "Clear"},
            {"WorkerShowPanel.removeGreaterButton.text", "Remove greater"},
            {"WorkerUpdateFrame.updateWorkerButton.text", "update"},
            {"WorkerUpdateFrame.workerUpdateCancelButton.text", "cancel"},
            {"login", "Login"},
            {"login_btn", "Login"},
            {"register_btn", "Register"}
        };
    }
}