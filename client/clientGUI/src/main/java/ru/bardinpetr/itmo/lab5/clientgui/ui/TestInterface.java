package ru.bardinpetr.itmo.lab5.clientgui.ui;


import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show.WorkerShowPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.main.MainFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrameZ;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import javax.swing.text.DateFormatter;

public class TestInterface {
    public static void main(String[] args) {
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
        new WorkerAddFrameZ();
//        testPanel(new WorkerInfoPanelZ(new Worker()));
    }

    private static void testPanel(JPanel panel){
        var mainFrame = new JFrame();
        mainFrame.add(panel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
