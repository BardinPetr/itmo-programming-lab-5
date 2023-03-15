package ru.bardinpetr.itmo.lab5.client.controller.comands.common;

import ru.bardinpetr.itmo.lab5.client.controller.comands.common.AbstractLocalCommand;
import ru.bardinpetr.itmo.lab5.client.tui.UIReceiver;

public interface UILocalCommand {

    void setUiReceiver(UIReceiver uiReceiver);
}
