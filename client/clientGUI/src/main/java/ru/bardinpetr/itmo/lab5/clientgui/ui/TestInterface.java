package ru.bardinpetr.itmo.lab5.clientgui.ui;


import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.api.APIConnectorFactory;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.show.OrganizationShowPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.login.LoginPage;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.main.MainFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.add.OrgAddFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.update.OrgUpdateFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.update.WorkerUpdateFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetWorkerIdsCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.UpdateCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.AuthCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.PasswordLoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;
import ru.bardinpetr.itmo.lab5.models.data.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

public class TestInterface {
    public static void main(String[] args) throws APIClientException {

        APIConnectorFactory.create();
        var loginCmd = new PasswordLoginCommand();
        loginCmd.setCredentials(new DefaultAuthenticationCredentials("u", "p"));
        APIProvider.getCredentialsStorage().setCredentials(
                new StoredJWTCredentials((JWTLoginResponse) (
                        (AuthCommand.AuthCommandResponse) APIProvider.getConnector().call(loginCmd)).getData()
                )
        );



//        UtilDateModel model = new UtilDateModel();
//        Properties p = new Properties();
//        p.put("text.today", "Today");
//        p.put("text.month", "Month");
//        p.put("text.year", "Year");
//        p.put("text.june", "june");
//        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//        JDatePickerImpl workerStartDateField = new JDatePickerImpl(datePanel,new DateLabelFormatter());
//
//        workerStartDateField.getModel().addChangeListener((e) -> {
//            var startDate = ((UtilDateModel) e.getSource());
//            if (startDate.isSelected()){
//                System.out.println(startDate.getValue());
////                worker.setStartDate(startDate.getValue());
//            }
//        });

//        SpinnerDateModel model = new SpinnerDateModel();
//        JSpinner spinner = new JSpinner(model);
//
//        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd.MMMM.yyyy");
//        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
//        formatter.setAllowsInvalid(false); // this makes what you want
//        formatter.setOverwriteMode(true);
//
//        var frame = new JFrame();
//        frame.add(spinner);
//        frame.pack();
//        frame.setVisible(true);
//        testPanel(new WorkerInfoPanelZ());
//        new MainFrameZ();

//        new OrgUpdateFrameZ(new Organization(1,"Itmo", OrganizationType.OPEN_JOINT_STOCK_COMPANY));
        var testWorker = new Worker(
                110,
                "u",
                ZonedDateTime.now(),
                3,
                "Artem",
                123f,
                new Date(),
                LocalDate.now(),
                new Coordinates(1, 2),
                new Organization(2, "", OrganizationType.PUBLIC),
                Position.CLEANER
        );
        new APICommandMenger().sendCommand(
                new GetWorkerIdsCommand(),
                new JFrame(),
                "WorkerInfoPanel.label8.text",
                (response) -> {
                    var res= (GetWorkerIdsCommand.GetWorkerIdsCommandResponse) response;
                    System.out.println(res.getResult().contains(testWorker.getId()));
                }
        );


//        new LoginPage(() -> {
//            new MainFrameZ();
//        });
        new WorkerUpdateFrameZ(testWorker, true);
//        testPanel(new OrganizationShowPanel());
    }

    private static void testPanel(JPanel panel){
        var mainFrame = new JFrame();
        mainFrame.add(panel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
