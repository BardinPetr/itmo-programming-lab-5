package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.paging;

import jiconfont.IconCode;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EventListener;

public class PagingTableControl extends Box {
    private final PagingConfigChangedListener listener;
    private final SpinnerNumberModel spinnerModel;
    private final JLabel pageLabel;
    private final JButton prevBtn;
    private final JButton firstBtn;
    private final JButton lastBtn;
    private final JButton nextBtn;

    private int rowCount = 0;
    private int pageSize = 5;
    private int page = 1;


    public PagingTableControl(PagingConfigChangedListener listener) {
        super(BoxLayout.LINE_AXIS);
        this.listener = listener;

        firstBtn = new JButton(getIcon(FontAwesome.ANGLE_DOUBLE_LEFT));
        firstBtn.setActionCommand("first");
        lastBtn = new JButton(getIcon(FontAwesome.ANGLE_DOUBLE_RIGHT));
        lastBtn.setActionCommand("last");
        nextBtn = new JButton(getIcon(FontAwesome.ANGLE_RIGHT));
        nextBtn.setActionCommand("next");
        prevBtn = new JButton(getIcon(FontAwesome.ANGLE_LEFT));
        prevBtn.setActionCommand("prev");

        firstBtn.addActionListener(this::onPageButtonClicked);
        lastBtn.addActionListener(this::onPageButtonClicked);
        nextBtn.addActionListener(this::onPageButtonClicked);
        prevBtn.addActionListener(this::onPageButtonClicked);

        pageLabel = new JLabel();
        pageLabel.setMinimumSize(new Dimension(200, 50));

        spinnerModel = new SpinnerNumberModel(pageSize, 1, 5, 5);
        spinnerModel.addChangeListener(this::onPageSizeSpinnerEvent);

        var pageSizeSpinner = new JSpinner(spinnerModel);
        pageSizeSpinner.setMaximumSize(new Dimension(80, 100));

        add(pageSizeSpinner);
        add(firstBtn);
        add(prevBtn);
        add(pageLabel);
        add(nextBtn);
        add(lastBtn);

        updateStatus();
        setVisible(true);
    }

    public int getPageCount() {
        return (int) Math.ceil((double) rowCount / pageSize);
    }

    public void setRowCount(int val) {
        rowCount = val;
        if ((page - 1) * pageSize > rowCount)
            page = rowCount / pageSize;
        updateStatus();
    }

    public void setMaxPageSize(int val) {
        if (val < 1)
            return;

        if (pageSize > val)
            spinnerModel.setValue(val);
        spinnerModel.setMaximum(val);

        updateStatus();
        fireUpdate();
    }

    private void onPageSizeSpinnerEvent(ChangeEvent changeEvent) {
        var newPageSize = (int) spinnerModel.getNumber();

        var lastStartRow = (page - 1) * pageSize;
        page = 1 + lastStartRow / newPageSize;

        pageSize = newPageSize;
        fireUpdate();
        updateStatus();
    }

    private void onPageButtonClicked(ActionEvent e) {
        int newPage = switch (e.getActionCommand()) {
            case "first" -> 1;
            case "last" -> getPageCount();
            case "next" -> page + 1;
            case "prev" -> page - 1;
            default -> page;
        };

        if (newPage > getPageCount() || newPage < 1) return;
        page = newPage;

        fireUpdate();
        updateStatus();
    }

    private void updateStatus() {
        pageLabel.setText("%d / %d".formatted(page, getPageCount()));
        var isNotEnd = page < getPageCount();
        var isNotStart = page > 1;
        firstBtn.setEnabled(isNotStart);
        prevBtn.setEnabled(isNotStart);
        lastBtn.setEnabled(isNotEnd);
        nextBtn.setEnabled(isNotEnd);
    }

    private void fireUpdate() {
        SwingUtilities.invokeLater(
                () -> listener.onChange(page - 1, pageSize)
        );
    }

    private Icon getIcon(IconCode code) {
        return IconFontSwing.buildIcon(code, 16);
    }

    public interface PagingConfigChangedListener extends EventListener {
        void onChange(int curPage, int pageSize);
    }
}
