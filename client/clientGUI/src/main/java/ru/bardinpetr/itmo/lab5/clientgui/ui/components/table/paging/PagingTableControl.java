package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.paging;

import jiconfont.IconCode;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class PagingTableControl extends Box {
    private final JLabel pageLabel;
    private final Consumer<Integer> pageChanged;
    private final JButton prevBtn;
    private final JButton firstBtn;
    private final JButton lastBtn;
    private final JButton nextBtn;
    private final SpinnerNumberModel spinnerModel;

    private Integer page = 1;
    private Integer pageCount = 1;

    public PagingTableControl(Consumer<Integer> pageChanged, Consumer<Integer> pageSizeChanged) {
        super(BoxLayout.LINE_AXIS);
        this.pageChanged = pageChanged;

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

        spinnerModel = new SpinnerNumberModel(5, 1, 5, 5);
        spinnerModel.addChangeListener(e -> pageSizeChanged.accept((Integer) spinnerModel.getNumber()));

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

    public void setMaxPageSize(int val) {
        if (val < 1)
            return;
        if (((int) spinnerModel.getValue()) > val)
            spinnerModel.setValue(val);
        spinnerModel.setMaximum(val);
        updateStatus();
    }

    public void setPageCount(int val) {
        pageCount = val;
        updateStatus();
    }

    private void updateStatus() {
        pageLabel.setText("%d / %d".formatted(page, pageCount));
        firstBtn.setEnabled(page > 1);
        lastBtn.setEnabled(page < pageCount);
        prevBtn.setEnabled(page > 1);
        nextBtn.setEnabled(page < pageCount);
    }

    private void onPageButtonClicked(ActionEvent e) {
        int newPage = page;
        switch (e.getActionCommand()) {
            case "first" -> newPage = 1;
            case "last" -> newPage = pageCount;
            case "next" -> newPage = page + 1;
            case "prev" -> newPage = page - 1;
        }

        if (newPage > pageCount || newPage < 1) return;
        page = newPage;

        pageChanged.accept(page - 1);
        updateStatus();
    }

    private Icon getIcon(IconCode code) {
        return IconFontSwing.buildIcon(code, 16);
    }
}
