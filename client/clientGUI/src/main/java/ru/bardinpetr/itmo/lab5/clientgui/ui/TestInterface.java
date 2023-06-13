package ru.bardinpetr.itmo.lab5.clientgui.ui;


import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.api.APIConnectorFactory;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.info.OrganizationInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.script.ScriptPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.main.MainFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.update.OrgUpdateFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.update.WorkerUpdateFrameZ;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.auth.AuthCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.PasswordLoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;
import ru.bardinpetr.itmo.lab5.models.data.*;

import javax.swing.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

public class TestInterface {
    public static void main(String[] args) throws APIClientException {
        UIResources.getInstance();

        APIConnectorFactory.create();
        var loginCmd = new PasswordLoginCommand();
        loginCmd.setCredentials(new DefaultAuthenticationCredentials("u", "p"));
        APIProvider.getCredentialsStorage().setCredentials(
                new StoredJWTCredentials((JWTLoginResponse) (
                        (AuthCommand.AuthCommandResponse) APIProvider.getConnector().call(loginCmd)).getData()
                )
        );

//        APIProvider.getPoolingEventSource()
//                .subscribe(new ResourceEventConsumer(System.out::println, "worker"));

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
                194,
                "u",
                ZonedDateTime.now(),
                3,
                "fewf",
                123f,
                new Date(),
                LocalDate.now(),
                new Coordinates(1, 2),
                new Organization(2, "2344", OrganizationType.OPEN_JOINT_STOCK_COMPANY),
                Position.ENGINEER
        );
        var testOrg = new Organization(
                122,
                "131232334",
                OrganizationType.OPEN_JOINT_STOCK_COMPANY);
        APIProvider.getPoolingEventSource().subscribe(System.out::println);

//        new LoginPage(MainFrameZ::new);
//        new WorkerUpdateFrameZ(testWorker, true);
//        });

//        System.out.println(Charset.defaultCharset());
//        new OrgUpdateFrameZ(testOrg);
//        new WorkerUpdateFrameZ(testWorker, false);
        new MainFrameZ();
//        testPanel(new ScriptPanel(() -> {}));
    }
    private static void testPanel(JPanel panel) {
        var mainFrame = new JFrame();
        mainFrame.add(panel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
